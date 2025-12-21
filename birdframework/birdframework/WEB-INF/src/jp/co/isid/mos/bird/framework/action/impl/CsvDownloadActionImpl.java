/**
 *
 */
package jp.co.isid.mos.bird.framework.action.impl;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.framework.action.CsvDownloadAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.logic.DownloadFinalLogic;
import jp.co.isid.mos.bird.framework.logic.DownloadInitLogic;

/**
 * CSVダウンロードアクション用
 *
 * 事前処理・事後処理機能が付いているCSVダウンロードアクションです。
 *
 * さらに、download()メソッド(ダウンロード メイン)処理中に発生したExceptionを
 * Trowするかしないかをクラス変数.Throw実行判断フラグを使用してコントロールを
 * することが可能です。
 * デフォルトではThrow実行判断フラグはtrueが設定され、ExceptionはThrowされます。
 * Throwさせない場合はこのアクションを定義したdiconファイルのpropertyの
 * exceptionThrowFlgにfalseを設定します。
 *
 * 記述例)　※COD残高一覧画面から抜粋
 *  <component name="codBalanceListCsvAction" class="jp.co.isid.mos.bird.framework.action.impl.CsvDownloadActionImpl" instance="request">
 *  	<property name="csvOutputDto">codBalanceListReqDto</property>
 *  	<property name="csvOutputLogic">codBalanceListCsvOutputLogic</property>
 *  	<property name="initLogic">null</property>
 *  	<property name="finalLogic">codBalanceListDownloadFinalLogic</property>
 *  	<property name="exceptionThrowFlg">false</property>
 *  </component>
 *
 * 更新:2007/07/12 xkinu
 * 　download()メソッド(ダウンロード メイン)処理中の
 * 　Exception発生時Throw処理実行判断ロジックの追加と
 *   クラス変数.判断フラグ：Exception発生時Throw実行判断処理フラグの追加
 *
 * @author xkinu
 *
 */
public class CsvDownloadActionImpl implements CsvDownloadAction {
    public static final String download_ACTION_ID = "BFW002A01";
    /* HttpServletResponse */
    private HttpServletResponse _httpServletResponse = null;

    /* ロジック【出力処理用】 */
    private CsvOutputLogic _outputLogic = null;
    /* ロジック【事前処理用】 */
    private DownloadInitLogic _initLogic = null;
    /* ロジック【事後処理用】 */
    private DownloadFinalLogic _finalLogic = null;

    /* DTO【出力用】 */
    private CsvOutputDto _csvOutputDto = null;

    /* 判断フラグ：Throw実行判断フラグ */
    private boolean exceptionThrowFlg = true;

    /* MAP【ロジックパラメーター】*/
    private Map _params = null;

    /**
     * HttpServletResponse設定処理
     */
    public void setResponse(HttpServletResponse request) {
        this._httpServletResponse = request;
    }
    /**
     * HttpServletResponse取得処理
     * @return HttpServletResponse
     */
    private HttpServletResponse getResponse() {
        return _httpServletResponse;
    }

    /**
     * ロジック【出力用】設定処理
     */
    public void setCsvOutputLogic(CsvOutputLogic outputLogic) {
        _outputLogic = outputLogic;
    }
    /**
     * ロジック【出力用】取得処理
     * @return CsvOutputLogic
     */
    private CsvOutputLogic getCsvOutputLogic() {
        return _outputLogic;
    }

    /**
     * DTO【出力用】設定処理
      */
    public void setCsvOutputDto(CsvOutputDto outputDto) {
        _csvOutputDto = outputDto;
    }
    /**
     * DTO【出力用】取得処理
     * @return
     */
    private CsvOutputDto getCsvOutputDto() {
        return _csvOutputDto;
    }

    /**
     * ロジック【事前処理用】設定処理
     */
    public void setInitLogic(DownloadInitLogic initLogic) {
        _initLogic = initLogic;
    }
    /**
     * ロジック【事前処理用】取得処理
     * @return
     */
    private DownloadInitLogic getInitLogic() {
        return _initLogic;
    }
    /**
     * ロジック【Exception発生時処理用】設定処理
     */
    public void setFinalLogic(DownloadFinalLogic FinalLogic) {
        _finalLogic = FinalLogic;
    }
    /**
     * ロジック【Exception発生時処理用】取得処理
     * @return
     */
    private DownloadFinalLogic getFinalLogic() {
        return _finalLogic;
    }
    /**
     * MAP【ロジックパラメーター】取得処理
     *
     * 指定パラメーター以外に下記のパラメーターを自動的に設定し、戻します。
     * ・BIRDユーザー情報
     * ・BIRD日付情報
     * ・CSV出力用DTO
     *
     * @return
     */
    private Map getParams() {
        if(_params == null) {
            _params = new HashMap();
        }
        _params.put(PK_USERINFO, getBirdUserInfo());
        _params.put(PK_DATEINFO, getBirdDateInfo());
        _params.put(PK_CSVOUTPUTDTO, getCsvOutputDto());
        return _params;
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

    /**
     * ダウンロード メイン処理
     * @see jp.co.isid.mos.bird.framework.action.CsvDownloadAction#download()
     */
    public void download() {
        Exception _exception = null;
        try {
            //１．ロジック【事前処理用】を実行する。
            if(getInitLogic() != null) {
                getInitLogic().execute(getParams());
            }
            //２．出力事前チェック処理を行う。
            getCsvOutputLogic().validate(getCsvOutputDto());
            //３．ロジック【出力用】から[[出力データ]]を取得する。
            List allData = getCsvOutputLogic().getOutputData(getCsvOutputDto());

            PrintWriter out = null;
            //４．ロジック【出力用】からファイル名を取得する。
            String filename = new String(getCsvOutputLogic().getFileName(
                    getCsvOutputDto()).getBytes("SJIS"), "ISO8859_1");
            HttpServletResponse response = getResponse();
            try {
                response.setContentType("application/x-csv;charset=Windows-31j");
                response.setHeader("Content-Disposition", "attachment;filename=\""
                        + filename + "\"");

                out = response.getWriter();
                //５．処理３[[出力データ]]件数分下記の処理をおこなう。
                for (int i = 0; i < allData.size(); i++) {
                    //1.処理３[[出力データ]]から[現行行データ]を抜き出す。
                    List row = (List) allData.get(i);

                    for (int j = 0; j < row.size(); j++) {

                        if (isFormula(row.get(j))) {
                            out.print(row.get(j));
                        } else {
                            out.print("\"" + row.get(j) + "\"");
                        }
                        if (j < row.size() - 1) {
                            out.print(",");
                        }
                    }
                    //2.処理1[現行行データ]を出力する。
                    out.println();

                }
                FacesContext context = FacesContext.getCurrentInstance();
                context.responseComplete();

            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
        catch (ApplicationException appEx) {
            _exception = appEx;
            if(isExceptionThrowFlg()) {
            	throw appEx;
            }
        }
        catch (Exception ex) {
        	_exception = ex;
            if(isExceptionThrowFlg()) {
            	throw new FtlSystemException("CSVダウンロード", null, ex);
            }
        }
        finally {
            if(getFinalLogic() != null) {
                getParams().put(PK_EXCEPTION, _exception);
                //Ａ．ロジック【事後処理用】を実行する。
                getFinalLogic().execute(getParams());
            }

        }
    }
    /**
     *
     * @param row
     * @return
     */
    private boolean isFormula(final Object row) {

        boolean result = false;

        if (row != null && row instanceof String) {
            String rowStr = (String) row;
            if (rowStr.indexOf("=\"") == 0) {
                result = true;
            }
        }
        return result;

    }
	/**
	 * @return exceptionThrowFlg を戻します。
	 */
	public boolean isExceptionThrowFlg() {
		return exceptionThrowFlg;
	}
	/**
	 * @param exceptionThrowFlg 設定する exceptionThrowFlg。
	 */
	public void setExceptionThrowFlg(boolean exceptionThrowFlg) {
		this.exceptionThrowFlg = exceptionThrowFlg;
	}

}
