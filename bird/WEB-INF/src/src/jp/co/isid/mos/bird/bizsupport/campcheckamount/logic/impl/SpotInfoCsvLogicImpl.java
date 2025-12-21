/*
 * 作成日: 2006/12/04
 */
package jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.common.CampCheckAmountCommon;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.common.CampCheckAmountConstants;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dao.UIBlockInfoDao;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dao.UICampaignInfoDao;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dao.UISpotQuantityInfoDao;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dto.CampCheckAmountSaveDto;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UIBlockInfo;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UICampaignInfo;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UISpotQuantityInfo;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 【CSV出力情報取得】ロジック
 *
 * @author xlee
 */
public class SpotInfoCsvLogicImpl implements CsvOutputLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BBS027L05";

    /**
     * スポット数量情報DAOを取得します。
     */
    private UISpotQuantityInfoDao uiSpotQuantityInfoDao;

    /**
     * キャンペーン情報DAOを取得します。
     */
    private UICampaignInfoDao uiCampaignInfoDao;

    /**
     * ブロック情報DAOを取得します。
     */
    private UIBlockInfoDao uiBlockInfoDao;

    /* DAO */
    private MstSibuDao mstSibuDao;

    NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);
    NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);

    /**
     * スポット数量情報DAOを取得します。
     * @return スポット数量情報DAO
     */
    public UISpotQuantityInfoDao getUISpotQuantityInfoDao() {
        return uiSpotQuantityInfoDao;
    }

    /**
     * スポット数量情報DAOを設定します。
     * @param uiSpotQuantityInfoDao スポット数量情報DAO
     */
    public void setUISpotQuantityInfoDao(UISpotQuantityInfoDao uiSpotQuantityInfoDao) {
        this.uiSpotQuantityInfoDao = uiSpotQuantityInfoDao;
    }

    /**
     * ファイル名取得処理
     */
    public String getFileName(CsvOutputDto csvOutputDto) {

        return CampCheckAmountConstants.CSV_FILE_NAME;
    }

    /**
     * キャンペーン情報DAOを取得します。
     * @return キャンペーン情報DAO
     */
    public UICampaignInfoDao getUICampaignInfoDao() {
        return uiCampaignInfoDao;
    }

    /**
     * キャンペーン情報DAOを設定します。
     * @param uiCampaignInfoDao キャンペーン情報DAO
     */
    public void setUICampaignInfoDao(UICampaignInfoDao uiCampaignInfoDao) {
        this.uiCampaignInfoDao = uiCampaignInfoDao;
    }

    /**
     * ブロック情報DAOを取得します。
     * @return ブロック情報DAO
     */
    public UIBlockInfoDao getUIBlockInfoDao() {
        return uiBlockInfoDao;
    }

    /**
     * ブロック情報DAOを設定します。
     * @param uiBlockInfoDao ブロック情報DAO
     */
    public void setUIBlockInfoDao(UIBlockInfoDao uiBlockInfoDao) {
        this.uiBlockInfoDao = uiBlockInfoDao;
    }

    /**
     * @return mstSibuDao を戻します。
     */
    public MstSibuDao getMstSibuDao() {
        return mstSibuDao;
    }

    /**
     * @param mstSibuDao mstSibuDao を設定。
     */
    public void setMstSibuDao(MstSibuDao mstSibuDao) {
        this.mstSibuDao = mstSibuDao;
    }

    /**
     * CSV出力メイン処理
     *
     * (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        //DTO
    	CampCheckAmountSaveDto dto = (CampCheckAmountSaveDto) csvOutputDto;

        String taishoCond = dto.getTmpTaishoCond();
        String companyCd = CampCheckAmountConstants.COMPANY_CD;
        String cmpNo = dto.getTmpTaishoCmpNo();
        String miseCd = dto.getTmpTaishoMiseCd();
        String onerCd = dto.getTmpTaishoOnerCd();
        String sibuCd = dto.getTmpTaishoSibuCd();
        String blockCd = dto.getTmpTaishoBlockCd();

       	List tmpCSVResult = new ArrayList();

       	if(CampCheckAmountConstants.USER_TYPE_HONBU.equals(
       			getBirdUserInfo().getMstUser().getUserTypeCd())) {
           	//対象条件がオーナーであれば
           	List tmpOnerCd = new ArrayList();
    		if(CampCheckAmountConstants.TAISHO_COND_OWNER.equals(taishoCond)) {
    			tmpOnerCd.add(onerCd);
    		}

       		tmpCSVResult = getUISpotQuantityInfoDao().getSpotQuantityInfo(
    				taishoCond, companyCd, cmpNo, miseCd, sibuCd, blockCd, getBirdDateInfo().getSysDate(), tmpOnerCd);

       	} else if(CampCheckAmountConstants.USER_TYPE_ONER.equals(
       			getBirdUserInfo().getMstUser().getUserTypeCd())) {

			List onerCdList = CampCheckAmountCommon.getOnerList(getBirdUserInfo(), companyCd);
       		tmpCSVResult = getUISpotQuantityInfoDao().getOnerSpotQuantityInfo(
       				companyCd, cmpNo, getBirdDateInfo().getSysDate(), onerCdList);
       	}

        //
        if (tmpCSVResult == null || tmpCSVResult.size() == 0) {
            //MSG【E0102】’該当データーがありません。’
             throw new NoResultException();
        }
        //
        List listCSV = new ArrayList();

        String campName = "";
        String sibuName = "";
        String blockName = "";
        String miseName = "";

        //キャンペーン名
		List tmpCamp = getUICampaignInfoDao().getCampaignInfo(
				CampCheckAmountCommon.getPosPrevDate(getBirdDateInfo().getSysDate()), getBirdDateInfo().getSysDate(),
				CampCheckAmountCommon.getPosEndDatePrev1(getBirdDateInfo().getSysDate()));
        for (Iterator ite = tmpCamp.iterator(); ite.hasNext();) {
        	UICampaignInfo campInfo = (UICampaignInfo) ite.next();
        	if(campInfo.getCmpNo().equals(cmpNo)) {
        		campName = campInfo.getCmpName();
        		break;
        	}
        }
        if(CampCheckAmountConstants.USER_TYPE_HONBU.equals(
       			getBirdUserInfo().getMstUser().getUserTypeCd())) {
	        if(CampCheckAmountConstants.TAISHO_COND_TENPO.equals(taishoCond)) {
	        	for (Iterator ite = tmpCSVResult.iterator(); ite.hasNext();) {
	                //Listからentityへキャストする
	            	UISpotQuantityInfo spotInfo = (UISpotQuantityInfo) ite.next();
		        	if(spotInfo.getMiseCd().equals(miseCd)) {
		        		miseName = spotInfo.getMiseNameKj();
		        		break;
		        	}
	            }
	        } else if(CampCheckAmountConstants.TAISHO_COND_SIBU.equals(taishoCond)) {
		        //支部名
		        List tmpSibu = getMstSibuDao().getSibuTorikomi(
		        		companyCd, getBirdUserInfo().getUserID(), getBirdUserInfo().isLimit());
		        for (Iterator ite = tmpSibu.iterator(); ite.hasNext();) {
		        	MstSibu sibuInfo = (MstSibu) ite.next();
		        	if(sibuInfo.getSibuCd().equals(sibuCd)) {
		        		sibuName = sibuInfo.getSibuName();
		        		break;
		        	}
		        }
		        //ブロック名
		        List tmpBlock = getUIBlockInfoDao().getBlockInfo();
		        for (Iterator ite = tmpBlock.iterator(); ite.hasNext();) {
		        	UIBlockInfo blockInfo = (UIBlockInfo) ite.next();
		        	if(blockInfo.getBlockCd().equals(blockCd)) {
		        		blockName = blockInfo.getBlockName();
		        		break;
		        	}
		        }
	        }
        }

        //
        settingHeader(listCSV, campName, sibuName, blockName, miseCd, miseName);

        //
        List listDataTitle = makeDataTitle();
        listCSV.add(listDataTitle);

        BigDecimal sumAmount = new BigDecimal(0);

        try {
            //
            for (Iterator ite = tmpCSVResult.iterator(); ite.hasNext();) {
                //
            	UISpotQuantityInfo entity = (UISpotQuantityInfo) ite.next();
                //１行分の[CSV出力データ]リスト作成
                List listData = make1RowData(entity);
                //上記で作成した[CSV出力データ]を[[CSV出力データ]]リストへ格納
                listCSV.add(listData);
                sumAmount = sumAmount.add(entity.getMiseYushoAmt());
            }
            //最後のライン
            List lastLine = new ArrayList();
            lastLine.add(CampCheckAmountConstants.CSV_HD_SUM);
            lastLine.add("");
            lastLine.add("");
            lastLine.add("");
            lastLine.add("");
            lastLine.add("");
            lastLine.add("");
            lastLine.add("");
            lastLine.add("");
            lastLine.add("");
            lastLine.add("");
            lastLine.add(numFmtdgt2.format(sumAmount.toString(), "#,##0"));
            listCSV.add(lastLine);
        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成 申込状況確認：結果データ設定処理");
        }
        //[[CSV出力データ]]リストをリターンする。
        return listCSV;
    }

    /**
     * CSVレイアウトヘッダ部を作成する。
     *
     * @param dto
     * @param listCsv
     */
    private void settingHeader(List listCsv, String campName,
    		String sibuName, String blockName,
    		String miseCd, String miseName) {

        List header1 = new ArrayList();
        List header2 = new ArrayList();
        List header3 = new ArrayList();
        List header4 = new ArrayList();

        try{
            // 一行目
            header1.add(CampCheckAmountConstants.CSV_HD_CMP);
            header1.add(campName);

            if(CampCheckAmountConstants.USER_TYPE_HONBU.equals(
           			getBirdUserInfo().getMstUser().getUserTypeCd())) {
	            // ニ行目
	            header2.add(CampCheckAmountConstants.CSV_HD_TAISHO_COND);
	            if(CampCheckAmountCommon.isNull(sibuName) &&
	            		CampCheckAmountCommon.isNull(blockName)) {
	            	header2.add(miseName);
	            } else {
	            	header2.add(sibuName + " " +blockName);
	            }
            } else if(CampCheckAmountConstants.USER_TYPE_ONER.equals(
           			getBirdUserInfo().getMstUser().getUserTypeCd())) {
            	header2.add("");
            }

            //三行目（空欄）
            header3.add("");
            //四行目（空欄）
            header4.add("");

            listCsv.add(header1);
            listCsv.add(header2);
            listCsv.add(header3);
            listCsv.add(header4);
        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成 申込状況確認：ヘッダ部作成処理");
        }
    }

    /**
     * CSVレイアウトデータ部タイトルを作成する。
     *
     * @return
     */
    private List makeDataTitle() {
        List list = new ArrayList();
        //五行目（データのヘッダ）
        list.add(CampCheckAmountConstants.CSV_HD_MISE_CD);
        list.add(CampCheckAmountConstants.CSV_HD_MISE_NM);
        list.add(CampCheckAmountConstants.CSV_HD_SHOHIN_CD);
        list.add(CampCheckAmountConstants.CSV_HD_SHOHIN_NM);
        list.add(CampCheckAmountConstants.CSV_HD_NISUGATA);
        list.add(CampCheckAmountConstants.CSV_HD_IRIME);
        list.add(CampCheckAmountConstants.CSV_HD_TANKA);
        list.add(CampCheckAmountConstants.CSV_HD_MUSYO);
        list.add(CampCheckAmountConstants.CSV_HD_YUSHO);
        list.add(CampCheckAmountConstants.CSV_HD_SPOT);
        list.add(CampCheckAmountConstants.CSV_HD_SUM);
        list.add(CampCheckAmountConstants.CSV_HD_AMOUNT);
        return list;
    }

    public void validate(CsvOutputDto csvOutputDto) {

    }

    /**
     * １行分のリスト作成取得処理
     *
     * @param entity
     * @return
     */
    private List make1RowData(UISpotQuantityInfo entity) {

        List listData = new ArrayList();
        //
        listData.add(entity.getMiseCd());
        //
        listData.add(entity.getMiseNameKj());
        //
        listData.add(entity.getShoCdDai());
        //
        listData.add(entity.getPosNameKna());
        //
        listData.add(entity.getPosNisugata());
        //
        listData.add(entity.getPosIrime());
        //
        listData.add(numFmtdgt2.format(entity.getTankaUri()));
        //
        listData.add(numFmtdgt0.format(entity.getMushoAmt().toString(), "#,##0"));
        //
        listData.add(numFmtdgt0.format(entity.getYushoEAmt().toString(), "#,##0"));
        //
        listData.add(numFmtdgt0.format(entity.getYushoAmt().toString(), "#,##0"));
        //
        listData.add(numFmtdgt0.format(entity.getAmtSum().toString(), "#,##0"));
        //
        listData.add(numFmtdgt2.format(entity.getMiseYushoAmt().toString(), "#,##0"));

        return listData;
    }

    /**
     * Seaser2Containaer取得処理
     *
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRDユーザー情報取得処理
     *
     * @return
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * BIRD日付情報取得処理
     *
     * @return
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
}
