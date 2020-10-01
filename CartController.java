package jp.co.internous.grapes.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.grapes.model.domain.TblCart;
import jp.co.internous.grapes.model.domain.dto.CartDto;
import jp.co.internous.grapes.model.form.CartForm;
import jp.co.internous.grapes.model.mapper.TblCartMapper;
import jp.co.internous.grapes.model.session.LoginSession;

@Controller
@RequestMapping("/grapes/cart")
public class CartController {
	
	// カート情報テーブルにアクセスするDAO
	@Autowired
	private TblCartMapper tblCartMapper;

	@Autowired
	private LoginSession loginSession;
	
	// Gsonインスタンスを生成
	Gson gson = new Gson();
	
	@RequestMapping("/")
	public String index(Model m) {
		
		// ユーザーIDを取得
		int userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
		
		// ユーザーに紐づくカート情報を取得
		List<CartDto> carts = tblCartMapper.findByUserId(userId);
		m.addAttribute("loginSession", loginSession);
		m.addAttribute("carts", carts);
		
		// cart.htmlを返す
		return "cart";
	}
	
	@RequestMapping("/add")
	// クライアントからカート情報を受け取ってDBのカート情報テーブルを操作後、カート画面を返す
	public String addCart(CartForm form, Model m) {
		
		// ユーザーIDを取得
		int userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
		
		// 取得したユーザーIDをセット
		form.setUserId(userId);
		
		// TblCartオブジェクトcartを生成
		TblCart cart = new TblCart(form);
		
		// 取得件数を格納する変数resultを定義
		int result = 0;
		// カート情報テーブルに追加する商品IDと一致するデータが存在するかチェック
		if (tblCartMapper.findCountByUserIdAndProductId(userId, form.getProductId()) > 0) {
			// 追加する商品IDと一致するデータが存在する場合、データを更新し件数をresultに代入
			result = tblCartMapper.update(cart);
		} else {
			// 追加する商品IDと一致するデータが存在しない場合、データを挿入し件数をresultに代入
			result = tblCartMapper.insert(cart);
		}
		
		// 挿入/更新した場合
		if (result > 0) {
			// ユーザーに紐づくカート情報を取得
			List<CartDto> carts = tblCartMapper.findByUserId(userId);
			m.addAttribute("loginSession", loginSession);
			m.addAttribute("carts", carts);
		}
		
		// cart.htmlを返す
		return "cart";
	}
	
	// 明示的に警告をオフ
	@SuppressWarnings("unchecked")
	// 戻り値がレスポンスのコンテンツになる
	@ResponseBody
	@RequestMapping("/delete")
	// チェックされたカート情報を受け取ってDBのカート情報テーブルから削除し、結果を真偽値で返す
	public boolean deleteCart(@RequestBody String checkedIdList) {
		
		// 削除件数を格納する変数resultを定義
		int result = 0;
		
		// JSON文字列をJavaオブジェクトに変換し、マップに格納
		Map<String, List<String>> map = gson.fromJson(checkedIdList, Map.class);
		// マップから要素を取り出し、リストに格納
		List<String> checkedIds = map.get("checkedIdList");
		// for文でリストcheckedIdsの要素を取り出す
		for (String id : checkedIds) {
			// 取り出した要素をint型に変換後、一致するデータを削除し件数をresultに代入
			result += tblCartMapper.deleteById(Integer.parseInt(id));
		}
		
		// 削除件数が1以上ならtrue、0ならfalseを返す
		return result > 0;
		
	}
	
}
