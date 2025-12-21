/**
 * 作成日: 2016/02/03
 */
package jp.co.isid.mos.bird.storeinfo.miseref.action.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutputActionImpl;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.storeinfo.miseref.action.MiseRefHistoryAction;
import jp.co.isid.mos.bird.storeinfo.miseref.dto.TOHistoryCsvOutputDto;


/**
 * 店舗のテイクオーバー履歴情報ダウンロードアクション
 * @author SHSC
 *
 */
public class MiseRefHistoryActionImpl extends CsvOutput2ActionImpl implements MiseRefHistoryAction {

	public static final String downloadCsv_ACTION_ID = "BSI001A08";

	private static final String MSG_CSV_DOWNLOAD = "CSVダウンロード";
	private TOHistoryCsvOutputDto historyDto;
	private CsvOutputLogic toHistoryCsvOutputLogic;
	private CsvOutputActionImpl csvOutputActionImpl;

	@Override
	public void downloadCsv() {

		S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");

        String condCompanyCd = (String)request.getParameter("miseRef:condCompanyCd");
        String condMiseCd = (String)request.getParameter("miseRef:condMiseCd");

        getHistoryDto().setCondCompanyCd(condCompanyCd);
        getHistoryDto().setCondMiseCd(condMiseCd);

		// TODO 自動生成されたメソッド・スタブ
		try {
			super.downloadCsv();
		} catch(IOException e) {
			throw new FtlSystemException(MSG_CSV_DOWNLOAD, "", e);
		}
	}


	public TOHistoryCsvOutputDto getHistoryDto() {
		return historyDto;
	}

	public void setHistoryDto(TOHistoryCsvOutputDto historyDto) {
		this.historyDto = historyDto;
	}

	public CsvOutputLogic getToHistoryCsvOutputLogic() {
		return toHistoryCsvOutputLogic;
	}

	public void setToHistoryCsvOutputLogic(CsvOutputLogic toHistoryCsvOutputLogic) {
		this.toHistoryCsvOutputLogic = toHistoryCsvOutputLogic;
	}

	public CsvOutputActionImpl getCsvOutputActionImpl() {
		return csvOutputActionImpl;
	}

	public void setCsvOutputActionImpl(CsvOutputActionImpl csvOutputActionImpl) {
		this.csvOutputActionImpl = csvOutputActionImpl;
	}
}
