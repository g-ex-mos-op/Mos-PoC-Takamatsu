/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.ordertimerequired.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.ordertimerequired.dao.UIOrderTimeRequiredDao;
import jp.co.isid.mos.bird.bizsupport.ordertimerequired.dto.RequestDto;
import jp.co.isid.mos.bird.bizsupport.ordertimerequired.entity.UIOrderTimeRequired;
import jp.co.isid.mos.bird.bizsupport.ordertimerequired.util.OrderTimeRequiredUtil;
import jp.co.isid.mos.bird.common.code.TaishoKikan;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * オーダー提供時間ダウンロードロジック
 * 作成日:2009/10/16
 * @author xkinu
 *
 */
public class CsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = OrderTimeRequiredUtil.SCREEN_ID+"L02";
	/** DAO【オーダー提供時間】*/
	private UIOrderTimeRequiredDao orderTimeRequiredDao;
	
    /**
     * ファイル名取得
     * 形式：ORDER_+店コード+.csv
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        RequestDto requestDto = (RequestDto) csvOutputDto;
    	return "ORDER_"+requestDto.getMiseCd()+".csv";
	}
	/**
	 * CSV出力処理
	 * 
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        //１．パラメータ．【ダウンロード用DTO】をDTO【自画面Request】へキャストします。
		RequestDto requestDto = (RequestDto) csvOutputDto;
		//２．変数.支部制限フラグ値へ本部ユーザーの場合はBIRDユーザ情報のリミット値を
		//    他のユーザはfalseを設定します。
		boolean limitFlg = UserType.HONBU.equals(getBirdUserInfo().getMstUser().getUserTypeCd())?getBirdUserInfo().isLimit():false;
		//２．DAO【検索結果取得】検索を実行し、検索結果List[[検索結果]]を取得します。
		List listCsvData = getOrderTimeRequiredDao().select(
				  getBirdUserInfo().getUserID(), limitFlg
				, requestDto.getCompanyCd(), requestDto.getMiseCd()
				, requestDto.getTaishoKikan(), requestDto.getKikanFrom(), requestDto.getKikanTo());
    	//３．処理２のList[[検索結果]]に該当データが存在しない場合は、NoResultExceptionを発生させる。
		if(listCsvData == null || listCsvData.size()==0) {
    		throw new NoResultException("");
    	}
		//４．処理２のList[[検索結果]]のインデックス0番目のEntity[[検索結果]]を取得します。
		UIOrderTimeRequired orderTimeRequired = (UIOrderTimeRequired)listCsvData.get(0);
		//５．処理４のEntity[[検索結果]].店名称をDTO【自画面Request】.店名称へ設定します。
		requestDto.setMiseNameKj(orderTimeRequired.getMiseNameKj());
		//６．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList();
        //７．条件項目ヘッダ作成します。
		addHeader(listCSV, requestDto);
        //８．明細部ヘッダー作成します。
		addListHeader(listCSV);
        //９．明細部データ作成します。
		addList(listCSV, listCsvData);
		//10．List[[CSV出力用リスト]]をリターンします。
		return listCSV;
	}
	/**
	 * ヘッダ部作成設定処理
	 * 
	 * @param listCSV List[[CSV出力用リスト]]
	 * @param requestDto
	 */
	private void addHeader(List listCSV, RequestDto requestDto) {
        //１． ヘッダ部1行目のList[[ヘッダ部1行目]]を作成します。
        List listHeader1Row = new ArrayList();
        //２．List[[ヘッダ部1行目]]へ下記の値を設定します。
        listHeader1Row.add("表示対象：");
        listHeader1Row.add(requestDto.getMiseCd()+ " " + requestDto.getMiseNameKj());
        listHeader1Row.add("");
        //３．List[[ヘッダ部1行目]]をList[[CSV出力用リスト]]へ格納します。
        listCSV.add(listHeader1Row);
        
        //４． ヘッダ部2行目のList[[ヘッダ部2行目]]を作成します。
        List listHeader2Row = new ArrayList();
        //５．List[[ヘッダ部2行目]]へ下記の値を設定します。
        listHeader2Row.add("対象期間：");
        //単日の場合は、「yyyy/mm/dd」。
        String taishoKikan = CommonUtil.formattYMDSlash(requestDto.getKikanFrom());
        if(TaishoKikan.DAYS.equals(requestDto.getTaishoKikan())) {
        	//期間指定の場合は、「yyyy/mm/dd ～ yyyy/mm/dd」
        	taishoKikan = taishoKikan+"～"+CommonUtil.formattYMDSlash(requestDto.getKikanTo());
        }
    	listHeader2Row.add(taishoKikan);
        listHeader2Row.add("");
        //６．List[[ヘッダ部2行目]]をList[[CSV出力用リスト]]へ格納します。
        listCSV.add(listHeader2Row);
        
        //７．空白行２行分をList[[CSV出力用リスト]]へ格納します。
        listCSV.add(new ArrayList());
        listCSV.add(new ArrayList());
	}
	/**
	 * データ項目名称格納処理
	 * 
	 * @param listCSV List[[CSV出力用リスト]]
	 */
	private void addListHeader(List listCSV) {
		//１．List[[データ項目ヘッダー部]]をインスタンス化します。 
		List listHeader1 = new ArrayList(0);
		//２．項目名称を設定します。
		listHeader1.add("営業日");
		listHeader1.add("時間帯区分");
		listHeader1.add("提供時間区分");
		listHeader1.add("Ｅ／Ｉ客数");
		listHeader1.add("Ｅ／Ｉ売上個数");
		listHeader1.add("Ｔ／Ｏ客数");
		listHeader1.add("Ｔ／Ｏ売上個数");
		listHeader1.add("ＴＥＬ客数");
		listHeader1.add("ＴＥＬ売上個数");
		listHeader1.add("Ｄ／Ｔ客数");
		listHeader1.add("Ｄ／Ｔ売上個数");
		listHeader1.add("その他客数");
		listHeader1.add("その他売上個数");
		//３．List[[データ項目ヘッダー部]]をList[[CSV出力用リスト]]へ格納します。
		listCSV.add(listHeader1);
	}
	/**
	 * 項目データ格納処理
	 * 
	 * @param listCSV List[[CSV出力用リスト]]
	 * @param listCsvData　List[[検索結果]]
	 */
	private void addList(List listCSV, List listCsvData) {
        NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
		//１．List[[検索結果]]の件数分、for文で下記の処理を行います。
		for(int i=0; i<listCsvData.size(); i++) {
			//for-1.List[[検索結果]]から現行インデックスのEntity[検索結果]を取得します。
			UIOrderTimeRequired entity = (UIOrderTimeRequired)listCsvData.get(i);
			//for-2.1行分のデータを格納するList[[出力情報]]を生成します。
			List rowData = new ArrayList(0);
			//for-3.処理2で生成したList[[出力情報]]へ下記の値を格納します。
			rowData.add(CommonUtil.formattYMDSlash(changeBlank(entity.getEigyoDt())));
			rowData.add(changeBlank(entity.getTmKbn()));
			rowData.add(changeBlank(entity.getTeikyoTmKbn()));
			rowData.add(numFmtdgt0.format(entity.getEatKyakusu()));
			rowData.add(numFmtdgt0.format(entity.getEatUriSu()));
			rowData.add(numFmtdgt0.format(entity.getTakeKyakusu()));
			rowData.add(numFmtdgt0.format(entity.getTakeUriSu()));
			rowData.add(numFmtdgt0.format(entity.getTelKyakusu()));
			rowData.add(numFmtdgt0.format(entity.getTelUriSu()));
			rowData.add(numFmtdgt0.format(entity.getDriveKyakusu()));
			rowData.add(numFmtdgt0.format(entity.getDriveUriSu()));
			rowData.add(numFmtdgt0.format(entity.getOtherKyakusu()));
			rowData.add(numFmtdgt0.format(entity.getOtherUriSu()));
			//for-4.1行分のList[[出力情報]]をList[[CSV出力用リスト]]へ格納します。
			listCSV.add(rowData);
		}
	}
    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {
        // 画面DTO
        if (csvOutputDto == null) {
            throw new NotNullException("自画面リクエストDTO");
        }
        if ((csvOutputDto instanceof RequestDto) == false) {
            throw new MissingConfigException("オーダー提供時間ダウンロード対象の自画面リクエストDTO");
        }
        RequestDto requestDto = (RequestDto)csvOutputDto;
        String miseCd = requestDto.getMiseCd();
        if(null == miseCd) {
        	throw new NotNullException("対象店舗", "miseCd", 0);
        }
		boolean isAlphabet = true;
        //店コードレングスチェック
        CodeVerifier codeVeri = new CodeVerifier(isAlphabet);
        //コードフォーマットチェック
        if(!codeVeri.validate(miseCd) || miseCd.length() > 5) {
            throw new InvalidInputException("対象店舗", "miseCd", 0);
        }
		CodeFormatter cdf = new CodeFormatter(5, "00000");
        cdf.setFormatPattern("00000");
        requestDto.setMiseCd(cdf.format(miseCd, true));

        //対象期間チェック
        if(requestDto.getTaishoKikan() == null) {
        	throw new NotNullException("対象期間", "taishoKikan", 0);
        }
        //期間指定Fromチェック
        String kikanFrom = requestDto.getKikanFrom();
        if(kikanFrom == null) {
        	String element = "期間指定";
        	if(TaishoKikan.DAYS.equals(requestDto.getTaishoKikan())) {
        		element = "期間指定FROM";
            }
        	throw new NotNullException(element, "kikanFrom", 0);
        }
        //対象期間が『期間指定』時のチェック 
        if(TaishoKikan.DAYS.equals(requestDto.getTaishoKikan())) {
            //期間指定Toチェック
        	String kikanTo = requestDto.getKikanTo();
            if(kikanTo == null) {
            	throw new NotNullException("期間指定To", "kikanTo", 0);
            }
            //期間指定From Toの期間整合性チェック
			if(kikanFrom.compareTo(kikanTo)>0) {
				throw new NotRelevantException("期間指定To","期間指定Fromと同じ又は未来日", "kikanTo", 0);
			}
            //期間指定From Toの期間上限チェック
			//From～Toに指定できる期間はFrom入れて７日以内です。
			String kikanFromAf7Day = "";
			try {
				kikanFromAf7Day = DateManager.getNextDate(kikanFrom, 6);
			}
			catch (Exception e) {
				throw new FtlSystemException("オーダー提供時間ダウンロードロジック"
						,"期間指定From Toの期間上限チェックのDateManager.getNextDateの処理ができませんでした。",e);
			}
            if(kikanTo.compareTo(kikanFromAf7Day)>0) {
            	throw new NotRelevantException("対象期間","7日以内", "kikanFrom", 0);
            }
        }
        
	}
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
	/**
	 * @return orderTimeRequiredDao を戻します。
	 */
	public UIOrderTimeRequiredDao getOrderTimeRequiredDao() {
		return orderTimeRequiredDao;
	}
	/**
	 * @param orderTimeRequiredDao を クラス変数orderTimeRequiredDaoへ設定します。
	 */
	public void setOrderTimeRequiredDao(UIOrderTimeRequiredDao orderTimeRequiredDao) {
		this.orderTimeRequiredDao = orderTimeRequiredDao;
	}
	/**
	 * null値の場合ブランクを返す。
	 * @param value
	 * @return
	 */
	private String changeBlank(String value) {
		return value == null?"":value;
	}

}
