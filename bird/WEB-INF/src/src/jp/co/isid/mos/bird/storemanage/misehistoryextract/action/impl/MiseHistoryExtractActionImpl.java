/*
 * 作成日: 2016/02/14
 */
package jp.co.isid.mos.bird.storemanage.misehistoryextract.action.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.action.impl.ExcelOutputActionImpl;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.ExcelOutputLogic;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.action.MiseHistoryExtractAction;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.dto.MiseHistoryExtractDto;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.logic.impl.MiseHistoryExtractExcelLogicImpl;

/**
 * 店マスタ履歴抽出画面アクション
 */
public class MiseHistoryExtractActionImpl extends CsvOutput2ActionImpl implements MiseHistoryExtractAction {

	/* アクションID */
	public static String initialize_ACTION_ID = "BSI009A01";
	public static String callMiseForm_ACTION_ID = "BSI009A02";
	public static String downloadCsv_ACTION_ID = "BSI009A03";
	public static String downloadExcel_ACTION_ID = "BSI009A04";
	/* ビューID */
	private static final String VIEWID_HISTORY = "BSI009V01"; // 店マスタ履歴抽出画面
	private static final String VIEWID_MISESEARCH = "BCO008V01"; // 店選択画面
	/* Action */
	private ExcelOutputActionImpl excelAction = new ExcelOutputActionImpl();
	/* Dto */
	private MiseHistoryExtractDto miseHistoryExtractDto;
	private MiseSearchDto miseSearchDto;
	/* Logic */
	private MiseHistoryExtractExcelLogicImpl excelOutputLogic;
	private HttpServletResponse httpServletResponse;

	/**
	 * 初期処理
	 */
	public String initialize() {
		if (getPullDownMenuDto().isClearFlg()) {
			getMiseHistoryExtractDto().setCondMiseCd("");
			getPullDownMenuDto().setClearFlg(false);
		}

		// 店検索戻り値のセット
		if (getMiseSearchDto().isActionFlg()) {
			getMiseHistoryExtractDto().setCondMiseCd(getMiseSearchDto().getMiseCd());
			getMiseSearchDto().setActionFlg(false);
		}

		return null;
	}

	/**
	 * 選択処理
	 */
	public String callMiseForm() {
		MiseSearchDto miseSearchDto = getMiseSearchDto();
		miseSearchDto.setNavigationCase(VIEWID_HISTORY);
		miseSearchDto.setInitialFlag(true);
		return VIEWID_MISESEARCH;
	}

	/**
	 * ダウンロード(CSV)処理
	 */
	public void downloadCsv() throws IOException {
		String miseCd = getMiseHistoryExtractDto().getCondMiseCd();
		if (miseCd.isEmpty()) {
			throw new NotNullException("店コード");
		}

		// CSVダウンロード
		super.downloadCsv();
	}

	/**
	 * ダウンロード(Excel)処理
	 */
	public void downloadExcel() throws IOException {
		String miseCd = getMiseHistoryExtractDto().getCondMiseCd();
		if (miseCd.isEmpty()) {
			throw new NotNullException("店コード");
		}

		// Excel出力内容を設定
		ExcelOutputLogic logic = getExcelOutputLogic();
		MiseHistoryExtractDto dto = getMiseHistoryExtractDto();
		excelAction.setExcelOutputDto(dto);
		excelAction.setExcelOutputLogic(logic);
		excelAction.setHttpServletResponse(httpServletResponse);

		// Excel出力
		excelAction.downloadExcel();
	}

	/**
	 * @return miseHistoryExtractDto を戻します。
	 */
	public MiseHistoryExtractDto getMiseHistoryExtractDto() {
		return miseHistoryExtractDto;
	}

	/**
	 * @param miseHistoryExtractDto
	 *            miseHistoryExtractDto を設定。
	 */
	public void setMiseHistoryExtractDto(MiseHistoryExtractDto miseHistoryExtractDto) {
		this.miseHistoryExtractDto = miseHistoryExtractDto;
	}

	/**
	 * @return miseSearchDto を戻します。
	 */
	public MiseSearchDto getMiseSearchDto() {
		return miseSearchDto;
	}

	/**
	 * @param miseSearchDto
	 *            miseSearchDto を設定。
	 */
	public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
		this.miseSearchDto = miseSearchDto;
	}

	/**
	 * @return excelOutputLogic
	 */
	public MiseHistoryExtractExcelLogicImpl getExcelOutputLogic() {
		return excelOutputLogic;
	}

	/**
	 * @param excelOutputLogic
	 *            excelOutputLogic を設定。
	 */
	public void setExcelOutputLogic(MiseHistoryExtractExcelLogicImpl excelOutputLogic) {
		this.excelOutputLogic = excelOutputLogic;
	}

	/**
	 * @return httpServletResponse
	 */
	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	/**
	 * @param httpServletResponse
	 *            httpServletResponse を設定。
	 */
	public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
	}

	/**
	 * @return S2Container を戻します。
	 */
	private S2Container getS2Container() {
		return SingletonS2ContainerFactory.getContainer();
	}
}