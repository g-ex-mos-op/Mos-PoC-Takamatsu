/**
 * 
 */
package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.common.BlockMaintenanceUtil;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.dao.CodBlockDao;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.CodBlock;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.ConditionLogic;
import jp.co.isid.mos.bird.common.entity.MstUserCompany;
import jp.co.isid.mos.bird.common.logic.GetSibuHoyuTenpoLogic;
import jp.co.isid.mos.bird.common.logic.GetUserCompanyLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 条件項目情報の取得ロジック
 * 
 * 2007/05/10 支部プルダウンを店舗の存在する支部のみに変更
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = BlockMaintenanceUtil.LOGIC_ID_CONDITION;
    /** ロジック【ユーザ所属会社リスト取得】*/
    private GetUserCompanyLogic getUserCompanyLogic = null;
    /** ロジック【支部の取得】*/
    private GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic = null;
    /** DAO【ブロックリスト】*/
    private CodBlockDao blockMainteCodBlockDao;
    
    /**
     * 事前条件処理
     * @param map
     * @throws MissingDataException
     */
    private void validate(final Map params) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
    }
	/**
	 * 実行処理
     * 
     * @param params
     * @return Map 
	 */
	public Map execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        //２．リターン値Mapをインスタンス化する。
        Map remap = new HashMap();
        //ユーザー情報
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        String userId = userInfo.getUserID();
        
        //３．ロジック【会社リスト】．検索 を実行し、
        //    実行結果[[会社リスト]]を取得する。
        List listCompany = getGetUserCompanyLogic().execute(userId, null);
        //４．処理３の実行結果をリターン値Mapへ格納する。
        remap.put(RK_LIST_COMPANY, listCompany);
        
        //５．ロジック【支部の取得】．検索 を実行し、
        //    実行結果[[支部リスト]]を取得する。
    	//   パラメーター：[会社リスト].会社コード
        String companyCd = ((MstUserCompany)listCompany.get(0)).getCompanyCd();
        List listSibu = getSibuHoyuTenpoLogic().execute(companyCd, "", false);
        //６．実行結果[[支部リスト]]をリターン値Mapへ格納する。
        remap.put(RK_LIST_SIBU, listSibu);
        
        //７．ロジック【ブロックリスト】．検索 を実行し、
        //    実行結果[[ブロックリスト]]を取得する。
        List listBlock = getBlockMainteCodBlockDao().select(false);
        //８．処理３の実行結果をリターン値Mapへ格納する。
        remap.put(RK_LIST_BLOCK, listBlock);

        //９．[[対象ブロックリスト]]を生成する。
        List listDispBlock = createListDispBlock(listBlock);
        //１０．処理３の実行結果をリターン値Mapへ格納する。
        remap.put(RK_LIST_DISPBLOCK, listDispBlock);
        
        //１１．リターン値Mapをリターンする。
        return remap;
	}
	/**
	 * 対象ブロックリスト生成処理
	 * 
	 * 引数ブロックリストの値をコピーし、
	 *    インデックス0へ『すべて：""(空)』を追加。
	 *    インデックス1へ『未登録："000"(空)』を追加。
	 * @param listBlock
	 * @return
	 */
	private static List createListDispBlock(List listBlock) {
		
		//１．[[対象ブロックリスト]]用にListを生成する。
		List listDispBlock = new ArrayList();
		
		//２．インデックス0番目の[対象ブロックリスト]を生成する。
		CodBlock entity0 = new CodBlock();
		//処理２の[対象ブロックリスト]へ『すべて：""(空)』を設定する。
		entity0.setBlockCd(BlockMaintenanceUtil.BLOCK_CD_ALL);
		entity0.setBlockName("すべて");
		
		//３．[[対象ブロックリスト]]のインデックス0へ追加。
		listDispBlock.add(0, entity0);
		
		//４．[対象ブロックリスト(CodBlock)]を生成する。
		CodBlock entity1 = new CodBlock();
		
		//５．処理４の[対象ブロックリスト]へ『未登録："000"』を設定する。
		entity1.setBlockCd(BlockMaintenanceUtil.BLOCK_CD_MISETTEI);
		entity1.setBlockName("未設定");
		listDispBlock.add(1, entity1);
		
		//６．引数[[ブロックリスト]]から値をすべてコピーする。
		for(int i=0; i<listBlock.size(); i++) {
			//1．コピー元[対象ブロックリスト]を取得する。
			CodBlock eMoto = (CodBlock)listBlock.get(i);
			
			//2．[対象ブロックリスト]を生成する。
			CodBlock entity = new CodBlock();
			//処理2の[対象ブロックリスト]へ『すべて：""(空)』を設定する。
			entity.setBlockCd(eMoto.getBlockCd());
			entity.setBlockName(eMoto.getBlockName());
			
			//3．[[対象ブロックリスト]]のインデックス0へ追加。
			listDispBlock.add(entity);
		}
		return listDispBlock;
	}
	/**
	 * @return getSibuLogic を戻します。
	 */
	public GetSibuHoyuTenpoLogic getSibuHoyuTenpoLogic() {
		return sibuHoyuTenpoLogic;
	}
	/**
	 * @param getSibuLogic 設定する getSibuLogic。
	 */
	public void setSibuHoyuTenpoLogic(GetSibuHoyuTenpoLogic getSibuLogic) {
		this.sibuHoyuTenpoLogic = getSibuLogic;
	}
	/**
	 * @return getUserCompanyLogic を戻します。
	 */
	public GetUserCompanyLogic getGetUserCompanyLogic() {
		return getUserCompanyLogic;
	}
	/**
	 * @param getUserCompanyLogic 設定する getUserCompanyLogic。
	 */
	public void setGetUserCompanyLogic(GetUserCompanyLogic getUserCompanyLogic) {
		this.getUserCompanyLogic = getUserCompanyLogic;
	}
	/**
	 * @return blockMainteCodBlockDao を戻します。
	 */
	public CodBlockDao getBlockMainteCodBlockDao() {
		return blockMainteCodBlockDao;
	}
	/**
	 * @param blockMainteCodBlockDao 設定する blockMainteCodBlockDao。
	 */
	public void setBlockMainteCodBlockDao(CodBlockDao blockMainteCodBlockDao) {
		this.blockMainteCodBlockDao = blockMainteCodBlockDao;
	}

}
