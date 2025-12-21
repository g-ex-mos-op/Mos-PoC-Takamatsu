/*
 * 作成日: 2006/04/13
 *
 */
package jp.co.isid.mos.bird.bizreport.groupsuiiref.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.common.GroupSuiiRefUtil;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * CSVダウンロードロジック
 * 
 * @author xkinu
 */
public class CsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = GroupSuiiRefUtil.LOGIC_ID_CSVOUTPUT;
    protected NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0, true);
    protected NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

    /** LOGIC【検索結果取得処理】*/
    private SearchLogic groupSuiiRefSearchLogic;
    
    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        SuiiRefParameterDto paramDto = (SuiiRefParameterDto) csvOutputDto;
		//１．月次タブのCSVの場合は下記の処理でCSVファイル名をリターンします。
		if(paramDto.isSuiiTypeGepo()) {
			if(TaishoKikan.CODE_NENDO.equals(paramDto.getTaishoKikan())) {
				//1.年度の月次タブのCSVの場合は、"SUIIMONTH"+DTO【検索結果】.期間指定(yyyy)+".csv"の文字列をリターンします。
				return "SUIIMONTH"+paramDto.getKikanSitei()+".csv";
			}
			//2.任意月指定の月次タブのCSVの場合は、"SUIIMONTHtab_gepo.csv"の文字列をリターンします。
			return "SUIIMONTHtab_gepo.csv";
		}
		else {
		//２.月次タブ以外のCSVの場合は、"SUIIDAY"+DTO【検索結果】.フォーカスタブ+".csv"の文字列をリターンします。
            return "SUIIDAY"+paramDto.getFocusTab()+".csv";
        }
	}

	/*
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        //0.
		SuiiRefParameterDto dto = (SuiiRefParameterDto)csvOutputDto;
		boolean isCsv = true;
		//1.LOGIC【検索結果取得】を実行し、戻り値DTO[検索結果]を取得します。
		SuiiRefResultDto suiiRefResultDto =  getGroupSuiiRefSearchLogic().execute(
				isCsv, dto, null);

		List listCsv = new ArrayList(0);
        List suiiList = null;
        //換算ボタン実行時の四捨五入単位を取得
        int pointCount = GroupSuiiRefUtil.countPattrn(dto).intValue();
        
        if(dto.isKansan()) {
            suiiList = GroupSuiiRefUtil.changeCurrency(dto,suiiRefResultDto).getListFocusTabResult();
        } else {
            suiiList = suiiRefResultDto.getListFocusTabResult();
        }
        
		SuiiRefUtil.csvSetHeader(getBirdUserInfo(), dto, suiiRefResultDto.getFocusUITabData(), listCsv);

        //明細部項目ヘッダ
		//modify xou.zoubun 2019/11/13 listCsv.add(SuiiRefUtil.csvGetDataTitle(dto));
		listCsv.add(SuiiRefUtil.csvGetDataTitle(dto, true));
        //データ設定処理
        addData(dto, suiiList, listCsv, pointCount);
		return listCsv;
	}

	/**
	 * 推移データ格納処理
	 * 
	 * @param dto
	 * @param listEntity
	 * @param listCsv
     * @param pointCount
	 */
	private void addData(SuiiRefParameterDto dto, List listEntity, List listCsv, int pointCount) {
        //明細データ
        for (int i=0; i<listEntity.size(); i++) {
            List listRow = new ArrayList(0);
            //データ設定処理
            SuiiRefUtil.csvSetData((SuiiRefUIEntity)listEntity.get(i), listRow
//modify 2019/07/18 #35 USI張  begin
//            		, dto.isSuiiTypeGepo(), dto.isSelectHosei(), dto.getTaishoJoken().equals(TaishoJoken.CODE_MISE), pointCount);
            		, dto.isSuiiTypeGepo(), dto.isSelectHosei(), dto.getTaishoJoken().equals(TaishoJoken.CODE_MISE),dto.getNebikiFlg(), pointCount);
//modify 2019/07/18 #35 USI張  end
            listCsv.add(listRow);
        }
    }
    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {
        // 画面DTO
        if (csvOutputDto == null) {
            throw new NotNullException("グループ業績売上推移表 画面DTO");
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
	 * LOGIC【検索結果取得処理】
	 * @return クラス変数groupSuiiRefSearchLogic を戻します。
	 */
	public SearchLogic getGroupSuiiRefSearchLogic() {
		return groupSuiiRefSearchLogic;
	}

	/**
	 * LOGIC【検索結果取得処理】
	 * @param searchLogic を クラス変数groupSuiiRefSearchLogicへ設定します。
	 */
	public void setGroupSuiiRefSearchLogic(SearchLogic searchLogic) {
		this.groupSuiiRefSearchLogic = searchLogic;
	}
}
