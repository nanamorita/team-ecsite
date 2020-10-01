package jp.co.internous.grapes.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.internous.grapes.model.domain.TblCart;
import jp.co.internous.grapes.model.domain.dto.CartDto;

@Mapper
public interface TblCartMapper {
	
	// ユーザーに紐づくカート情報を取得し、取得した結果をリストCartDtoに返す
	List<CartDto> findByUserId(int userId);
	
	//  カート情報を挿入し、挿入した件数を返す
	int insert(TblCart cart);
	
	// カート情報を更新し、更新した件数を返す
	int update(TblCart cart);
	
	// 仮ユーザーIDに紐づくカート情報をユーザーIDに紐づけ
	int updateUserId(@Param("userId") int userId, @Param("tmpUserId") int tmpUserId);
	
	// ユーザーに紐づくカート情報を取得し、取得した件数を返す
	int findCountByUserId(int userId);
	
	// ユーザーに紐づくカート情報から追加する商品IDと一致するデータを取得し、取得した件数を返す
	int findCountByUserIdAndProductId(@Param("userId") int userId, @Param("productId") int productId);
	
	// チェックされたカート情報を削除し、削除した件数を返す
	int deleteById(int id);
	
	// ユーザーに紐づくカート情報を削除し、削除した件数を返す
	int deleteByUserId(int userId);
	
}
