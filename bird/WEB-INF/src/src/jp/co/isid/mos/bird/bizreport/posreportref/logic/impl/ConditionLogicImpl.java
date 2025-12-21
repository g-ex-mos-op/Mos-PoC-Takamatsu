/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportref.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.SessionDto;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.LatestDateLogic;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.SearchMiseListLogic;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.CodHyojiTaishoDao;
import jp.co.isid.mos.bird.common.dao.MstOnerDao;
import jp.co.isid.mos.bird.common.entity.MstOner;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;

/**
 * 条件情報取得処理
 * 
 * 条件項目情報を取得生成し、セッション情報保持ＤＴＯへ設定する処理を行います。
 * 作成日:2010/10/25
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /*LOGIC_ID**/
    public static final String LOGIC_ID = "BBR006L06";
    /*モス会社コード*/
    private static final  String COMPANY_MOS = "00";
    
    /*LOGIC[最新日付取得]**/
    private LatestDateLogic latestDateLogic;
    /** LOGIC【店一覧検索】*/
    private SearchMiseListLogic posReportRefSearchMiseListLogic;
    /* DAO【表示対象情報】*/
    private CodHyojiTaishoDao codHyojiTaishoDao;
    /* DAO【オーナーマスタ情報】*/
    private MstOnerDao mstOnerDao;
    
	/**
	 * 実行処理
	 * 
	 * @param birdUserInfo ユーザー情報
	 * @param sessionDto   DTO【セッション情報保持】
	 * @param requestDto   DTO【リクエスト情報保持】
	 */
	public void execute(BirdUserInfo birdUserInfo
			, SessionDto sessionDto
			, PosReportRefDto requestDto) 
	{
        //１．DTO【セッション情報保持】のクリア処理を行います。
        sessionDto.clear();
		//２．DTO【リクエスト情報保持】のクリア処理を行います。
        requestDto.clear();
        //３．DTO【リクエスト情報保持】.会社コードへモスフードサービス('00')のコードを設定します。
        requestDto.setCompanyCd(COMPANY_MOS);
        //最新日付取得
		requestDto.setLatestDate(getLatestDateLogic().execute(requestDto));
		//４．パラメーター.ユーザー情報.ユーザータイプコード＝”本部”の場合、
        //    下記の処理を行います。
		if(UserType.isHonbu(birdUserInfo.getMstUser().getUserTypeCd())) {
			executeHonbu(birdUserInfo, sessionDto, requestDto);	
		}
		//５．パラメーター.ユーザー情報.ユーザータイプコード＝”オーナー”の場合、
		//    下記の処理を行います。
		else if(UserType.isOner(birdUserInfo.getMstUser().getUserTypeCd())) {
			executeOner(birdUserInfo, sessionDto, requestDto);	
		}
	}
	/**
	 * 本部用実行処理
	 * 
	 * @param birdUserInfo ユーザー情報
	 * @param sessionDto   DTO【セッション情報保持】
	 * @param requestDto   DTO【リクエスト情報保持】
	 */
	private void executeHonbu(
			  BirdUserInfo birdUserInfo
			, SessionDto sessionDto
			, PosReportRefDto requestDto)
	{		
		//１．変数.List[[表示対象]]を宣言します。
		List listHyojiTaisho = new ArrayList(0);
		//２．COD【対象条件】.プルダウン取得で、List[[対象条件]]を取得し、
		//        パラメータ.DTO【セッション情報保持】.List[[対象条件]]へ設定します。
		sessionDto.setListTaishoJoken(TaishoJoken.getPullDownList());
		//３.DAO【表示対象情報】.支部情報検索を実行し、戻り値List[[支部]]を取得します。
		List listSibu = getCodHyojiTaishoDao().selectSibu(requestDto.getCompanyCd()
			, birdUserInfo.getUserID()
			, birdUserInfo.getMstUser().getUserTypeCd()
			, birdUserInfo.isLimit());
		//４．処理１の戻り値List[[支部]]のサイズが1以上の場合、下記の処理を行います。
		if (listSibu.size()>0) {
			//Ⅰ．新規で支部リスト保持用にUILists[支部リスト用情報]をインスタンス化します。
			UILists hyojiTaishoInfo = new UILists(
					  TaishoJoken.SIBU_CD
					, TaishoJoken.getName(TaishoJoken.SIBU_CD));
			//Ⅱ．処理ⅠのUILists[支部リスト用情報].リストデータへ
			//    処理３で取得したList[[支部]]を設定します。
			hyojiTaishoInfo.setListData(listSibu);
			//Ⅲ．処理１の変数.List[[表示対象]]へ処理ⅠのUILists[支部リスト用情報]を追加します。
			listHyojiTaisho.add(hyojiTaishoInfo);
		}
		//５.DAO【表示対象情報】.エリア大情報検索を実行し、戻り値List[[エリア大]]を取得します。
		List listAreaDai = getCodHyojiTaishoDao().selectAreaDai(requestDto.getCompanyCd()
			, birdUserInfo.getUserID()
			, birdUserInfo.getMstUser().getUserTypeCd()
			, birdUserInfo.isLimit());
		//６．処理１の戻り値List[[エリア大]]のサイズが1以上の場合、下記の処理を行います。
		if (listAreaDai.size()>0) {
			//Ⅰ．新規でエリア大リスト保持用にUILists[エリア大リスト用情報]をインスタンス化します。
			UILists hyojiTaishoInfo = new UILists(
					  TaishoJoken.AREA_DAI
					, TaishoJoken.getName(TaishoJoken.AREA_DAI));
			//Ⅱ．処理ⅠのUILists[エリア大リスト用情報].リストデータへ
			//    処理５で取得したList[[エリア大]]を設定します。
			hyojiTaishoInfo.setListData(listAreaDai);
			//Ⅲ．処理１の変数.List[[表示対象]]へ処理ⅠのUILists[エリア大リスト用情報]を追加します。
			listHyojiTaisho.add(hyojiTaishoInfo);
		}
		//７．DTO【セッション情報保持】．List[[表示対象]]へ処理１の変数.List[[表示対象]]を設定します。
		sessionDto.setListHyojiTaisho(listHyojiTaisho);
		//８．処理１の変数.List[[表示対象]]のサイズが0以上の場合、
		//    DTO【リクエスト情報保持】.対象条件へインデックス0番目のUILists[表示対象].キーを設定します。
		if(listHyojiTaisho.size() > 0) {
			UILists hyojiTaishoInfo = (UILists)listHyojiTaisho.get(0);
			requestDto.setTaishoJoken(hyojiTaishoInfo.getKey());
		}
	}
	/**
	 * オーナー用実行処理
	 * 
	 * @param birdUserInfo ユーザー情報
	 * @param sessionDto   DTO【セッション情報保持】
	 * @param requestDto   DTO【リクエスト情報保持】
	 */
	private void executeOner(
			  BirdUserInfo birdUserInfo
				, SessionDto sessionDto
				, PosReportRefDto requestDto)
	{
		//１．DTO【リクエスト情報保持】．表示対象へブランク('')を設定します。
		requestDto.setHyojiTaisho("");
		//２．DTO【リクエスト情報保持】．表示対象名称へブランク('')を設定します。
		requestDto.setHyojiTaishoName("");
		//３．パラメータ．ユーザー情報．List[[ユーザーオーナー情報]]を取得します。
		List listOnerData = birdUserInfo.getUserOner();
		//４．処理３で取得したList[[ユーザーオーナー情報]]の件数分for文処理を行います。
		for (int i = 0; i < listOnerData.size(); i++) {
			//for-1.インデックス値ある[ユーザーオーナー情報]を取得します。
			UIUserOner userOner = (UIUserOner)listOnerData.get(i);
			//for-2.処理for-1で取得した[ユーザーオーナー情報].会社コードがモスフードサービス('00')の場合、
			//      下記の処理を行います。
			if (COMPANY_MOS.equals(userOner.getCompanyCd())) {
				//for-2-1.DAO【オーナーマスタ情報】.検索を実行し、Entity[オーナーマスタ情報]を取得します。
				MstOner mstOner = getMstOnerDao().selectOner(
						userOner.getCompanyCd(), userOner.getOnerCd());
				//for-2-2.DTO【リクエスト情報保持】．表示対象名称へ
				//        処理for-2-2のEntity[オーナーマスタ情報].オーナー名称漢字を設定します。
				requestDto.setHyojiTaisho(mstOner.getOnerCd());
				requestDto.setHyojiTaishoName(mstOner.getOnerNameKj());
				//for-2-3.for文処理を中断します。
				break;
			}
		}
        //ロジック【ＰＯＳ速報（店舗一覧)取得】を実行し、List[[ＰＯＳ速報（推移)]]を取得します。
        List listMiseList = getPosReportRefSearchMiseListLogic().execute(birdUserInfo, requestDto);
        //５－２．処理５－１で取得したList[[ＰＯＳ速報（店舗一覧)]]を
        //        DTO【リクエスト情報】.List[[ＰＯＳ速報（店舗一覧)]]へ設定します。
        requestDto.setListPosReportMiseInfo(listMiseList);
	}
	/**
	 * DAO【表示対象情報】取得処理
	 * @return DAO【表示対象情報】
	 */
	public CodHyojiTaishoDao getCodHyojiTaishoDao() {
		return codHyojiTaishoDao;
	}
	/**
	 * DAO【表示対象情報】設定処理
	 * @param codHyojiTaishoDao DAO【表示対象情報】
	 */
	public void setCodHyojiTaishoDao(CodHyojiTaishoDao codHyojiTaishoDao) {
		this.codHyojiTaishoDao = codHyojiTaishoDao;
	}
	public MstOnerDao getMstOnerDao() {
		return mstOnerDao;
	}
	public void setMstOnerDao(MstOnerDao mstOnerDao) {
		this.mstOnerDao = mstOnerDao;
	}
	public LatestDateLogic getLatestDateLogic() {
		return latestDateLogic;
	}
	public void setLatestDateLogic(LatestDateLogic latestDateLogic) {
		this.latestDateLogic = latestDateLogic;
	}
	public SearchMiseListLogic getPosReportRefSearchMiseListLogic() {
		return posReportRefSearchMiseListLogic;
	}
	public void setPosReportRefSearchMiseListLogic(
			SearchMiseListLogic posReportRefSearchMiseListLogic) {
		this.posReportRefSearchMiseListLogic = posReportRefSearchMiseListLogic;
	}

}
