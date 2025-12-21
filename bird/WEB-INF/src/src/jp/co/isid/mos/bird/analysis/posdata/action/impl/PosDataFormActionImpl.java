package jp.co.isid.mos.bird.analysis.posdata.action.impl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.analysis.posdata.action.PosDataFormAction;
import jp.co.isid.mos.bird.analysis.posdata.common.PosDataConstants;
import jp.co.isid.mos.bird.analysis.posdata.dto.PosDataFormDto;
import jp.co.isid.mos.bird.analysis.posdata.logic.PosDataFormLogic;
import jp.co.isid.mos.bird.analysis.posdata.logic.PosDataOutputLogic;
import jp.co.isid.mos.bird.analysis.posdata.logic.ZipOnerCheckLogic;
import jp.co.isid.mos.bird.analysis.posdata.logic.ZipOutputLogic;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class PosDataFormActionImpl implements PosDataFormAction {
         
    // アクションID定義
    public static final String initialize_ACTION_ID = "BDT001A01";
    public static final String search_ACTION_ID = "BDT001A02";
    public static final String execute_ACTION_ID = "BDT001A03";
    public static final String dataDownload_ACTION_ID = "BDT001A04";
    
    /* Content Disposition */
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    /* エラーメッセージ */
    private static final String ERR_MSG_SYSTEMERR = "ダウンロード";

    /* HttpServletResponse */
    private HttpServletResponse _httpServletResponse = null;
    
    private PosDataFormDto posDataFormDto;
    private PosDataFormLogic posDataFormLogic;
    private PosDataOutputLogic posDataOutputLogic;
    private ZipOutputLogic posDataZipOutputLogic;
    private ZipOnerCheckLogic posDataZipOnerCheck;
	private OwnerSearchDto ownerSearchDto;
    private PullDownMenuDto pullDownMenuDto;
    
    /**
     * 初期化処理
     */
    public String initialize() {
	    	
		// コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();

        // ユーザー関連情報
        BirdUserInfo birdUserInfo =
            	(BirdUserInfo) container.getComponent(BirdUserInfo.class);
		// ユーザ関連情報をDTOへ設定
		getPosDataFormDto().setBirdUserInfo(birdUserInfo);
		
        // 日付関連情報
        BirdDateInfo birdDateInfo =
            	(BirdDateInfo) container.getComponent(BirdDateInfo.class);

        // パラメタ取得
        String userType = birdUserInfo.getMstUser().getUserTypeCd();		// ユーザタイプ
        String sysDate = birdDateInfo.getSysDate();							// システム日付

        // 条件部情報取得
        Map map = getPosDataFormLogic().execute(userType,sysDate);

		// 取得したリスト情報をDTOへ設定
		getPosDataFormDto().setPosDataDtList((List) map.get(PosDataConstants.MAP_DATA_DT_LIST));
		
        // メニューDTO取得
        PullDownMenuDto pullDownMenuDto =
        	(PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        
        // メニューから遷移された場合
		if (pullDownMenuDto.isClearFlg()) {
			
            // ウィンドウID生成	
			getPosDataFormDto().updateWindowId();
            
			// 実行時のオーナーＩＤを初期化
			getOwnerSearchDto().setOnerCd(PosDataConstants.EMPTY);
			getPosDataFormDto().setOnerId(PosDataConstants.EMPTY);
			getPosDataFormDto().setSearchOnerId(PosDataConstants.EMPTY);
			getPosDataFormDto().setPosDataDt(PosDataConstants.EMPTY);
			getPosDataFormDto().setSearchPosDataDt(PosDataConstants.EMPTY);
            // クリアフラグ設定
			pullDownMenuDto.setClearFlg(false);			// メニュー画面遷移フラグ
			getPosDataFormDto().setDownloadFlag(false);// ダウンロード実行フラグ
        } else if (getOwnerSearchDto().getReturnKind() > 0) {
            // オーナー選択からの戻り処理
            getOwnerSearchDto().setReturnKind(OwnerSearchDto.RETURNKIND_INIT);
            // ウインドウID復元
            getPosDataFormDto().setWindowId(getOwnerSearchDto().getWindowId());
		}
		
		// オーナ選択情報取得
		if (ownerSearchDto.isActionFlag()) {
			getPosDataFormDto().setOnerId(ownerSearchDto.getOnerCd());
			ownerSearchDto.setActionFlag(false);
		}
		
		// 自画面へ遷移
		return null;
	}
    
    /**
     * オーナー検索処理
     * @return
     */
    public String search() {
    	    	
		// コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        // ユーザー関連情報
        BirdUserInfo birdUserInfo =
        	(BirdUserInfo) container.getComponent(BirdUserInfo.class);
        
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setNavigationCase(PosDataConstants.VIEW_INIT_ID);
        getOwnerSearchDto().setInitFlag(true);
        // 選択された会社コードセット
        List listCompany = new ArrayList();
        listCompany.add(birdUserInfo.getMstUser().getRCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        
        //ウィンドＩＤのセット
        getOwnerSearchDto().setNeedReturnKind(true);
        getOwnerSearchDto().setWindowId(getPosDataFormDto().getWindowId());
        
    	// 共通のオーナー検索画面を表示する。
    	return PosDataConstants.VIEW_ONER_ID;
    }
    
    /**
     * ＰＯＳ集信データ取得処理
     * @throws FtlSystemException
     * @return
     */
    public String execute(){
	          	
    	List outputList = new ArrayList();
    	// データ検索フラグの初期化
    	getPosDataFormDto().setDownloadFlag(false);
    	getPosDataFormDto().setOnerIdFlg(false);
    	
   		// 集信データ検索
    	outputList = getPosDataOutputLogic().execute(getPosDataFormDto(),getOwnerSearchDto());
	    // 検索結果をＤＴＯに保存
    	getPosDataFormDto().setPosDataList(outputList);
    	// ダウンロード起動フラグＯＮ
    	getPosDataFormDto().setDownloadFlag(true);
    	
        return PosDataConstants.VIEW_INIT_ID;
    }
    
    /**
     * ダウンロード メイン処理
     * 
     * @throws IOException
     * @throws ApplicationException
     */
    //public void dataDownload(List outputList) throws IOException {
    public void cvsDownload() throws IOException {
    	
    	List outputList = new ArrayList();
    	// データ検索フラグの初期化
    	getPosDataFormDto().setDownloadFlag(false);
    	getPosDataFormDto().setOnerIdFlg(false);
    	if(getPosDataZipOnerCheck().execute(getPosDataFormDto())) {
    		zipDownload();
    		return;
    	}
   		// 集信データ検索
    	outputList = getPosDataOutputLogic().execute(getPosDataFormDto(),getOwnerSearchDto());
    	
    	
        PrintWriter out = null;
        String filename = new String(getPosDataFormDto().getFileName().getBytes("SJIS"), "ISO8859_1");
        HttpServletResponse response = getResponse();
        try {
            response.setContentType("application/x-csv;charset=Shift_JIS");
            response.setHeader(CONTENT_DISPOSITION, "attachment;filename=\""
                    + filename + "\"");

            out = response.getWriter();

            for (int i = 0; i < outputList.size(); i++) {
            	// 改行は、\r\nで行う
                String row = (String) outputList.get(i) + PosDataConstants.KAIGYOU;
                out.print(row);
                //out.println();
            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.responseComplete();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        getPosDataFormDto().setDownloadFlag(false);
        
    }
    /**
     * ZIPファイルダウンロード処理
     * @param listOutputData
     */
    private void zipDownload() {
        try {
	    	//List[UILists[支部別出力情報]]を取得します。
	    	List listSibuOutputData = getPosDataZipOutputLogic().execute(getPosDataFormDto());
	    	
            String encodeWin31J = "Windows-31j";
        	String zipName = getPosDataZipOutputLogic().getFileName(getPosDataFormDto());
            //ContentType指定
            getResponse().setContentType(getPosDataZipOutputLogic().getContentType(getPosDataFormDto())+";charset="+encodeWin31J);
            //ファイル名の指定
            String filename = new String(zipName.getBytes(encodeWin31J), "ISO8859_1"); 
            getResponse().setHeader(CONTENT_DISPOSITION, "attachment;filename=\"" + filename + "\"");

            
    	    //Zipファイルダウンロード
            ZipOutputStream zipOutStream = null;
            try {

            	zipOutStream = new ZipOutputStream(new BufferedOutputStream(getResponse().getOutputStream()));
    	    	zipOutStream.setEncoding(encodeWin31J);
    	    	
    	    	System.out.println("zipファイル作成: 開始");
    	        for(Iterator ite  = listSibuOutputData.iterator(); ite.hasNext();) {
    				UILists sibuData = (UILists)ite.next();
			    	//zipファイルへ支部別のDATファイルを圧縮します。
					//2.対象支部分のdatファイルの生成
			    	archive(zipOutStream, sibuData);
			    	ite.remove();//高負荷対応
				}
		    	System.out.println("zipファイル作成: 終了");

		    	FacesContext context = FacesContext.getCurrentInstance();
	            context.responseComplete();
		    } catch (IOException e) {
		        throw new FtlSystemException("zipファイル作成中", "", e);
		    }
            finally {
	    		zipOutStream.close();
            }
        }
        catch (ApplicationException appEx) {
        	throw appEx;
        }
        catch (Exception ioe) {
            //例外処理
            throw new FtlSystemException(ERR_MSG_SYSTEMERR, "", ioe);
        }
    }
    /**
     * 支部別DATファイル作成圧縮処理
     * 
     * @param zipOutStream
     * @param sibuData 対象支部情報
     * @throws IOException
     */
    private void archive(final ZipOutputStream zipOutStream, UILists sibuData)  throws IOException{
        String datFilePath = getPosDataFormDto().getFileName();
       	datFilePath = sibuData.getKeyName()+"_"+datFilePath;
	    final ZipEntry entry = new ZipEntry(datFilePath);
		//3.zipファイルへdatファイルを格納
	    zipOutStream.putNextEntry(entry);
		//4.datファイルへ書き込み
		for(Iterator iteSibu  = sibuData.getListData().iterator(); iteSibu.hasNext();) {
			//改行は、\r\nで行う
			String row = (String) iteSibu.next() + PosDataConstants.KAIGYOU;
			zipOutStream.write(row.getBytes());
			iteSibu.remove();//高負荷対応
		}
		//5.datファイル書き込みクローズ
		zipOutStream.closeEntry();
    }
    /**
     * ダウンロード メイン処理
     * 
     * @throws IOException
     * @throws ApplicationException
     */
    public void dataDownload() throws IOException {

    	List outputList = getPosDataFormDto().getPosDataList();
        PrintWriter out = null;
        String filename = new String(getPosDataFormDto().getFileName().getBytes("SJIS"), "ISO8859_1");
        HttpServletResponse response = getResponse();
        
        if(outputList != null){
        
	        try {
	            response.setContentType("application/x-csv;charset=Shift_JIS");
	            response.setHeader(CONTENT_DISPOSITION, "attachment;filename=\""
	                    + filename + "\"");
	
	            out = response.getWriter();
	
	            for (int i = 0; i < outputList.size(); i++) {
	                String row = (String) outputList.get(i) + PosDataConstants.KAIGYOU;;
	                out.print(row);
	                //out.println();
	            }
	            FacesContext context = FacesContext.getCurrentInstance();
	            context.responseComplete();
	        } finally {
	            if (out != null) {
	                out.close();
	            }
	        }
        }
        // 検索結果の初期化
        getPosDataFormDto().setPosDataList(null);
    }

    /**
     * HttpServletResponse設定処理
     * 
     * @param httpServletResponse
     */
    public void setResponse(HttpServletResponse httpServletResponse) {
        this._httpServletResponse = httpServletResponse;
    }

    /**
     * HttpServletResponse取得処理
     * 
     * @return HttpServletResponse
     */
    private HttpServletResponse getResponse() {
        return _httpServletResponse;
    }
   
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}

	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
	}

	public PosDataOutputLogic getPosDataOutputLogic() {
		return posDataOutputLogic;
	}

	public void setPosDataOutputLogic(PosDataOutputLogic posDataOutputLogic) {
		this.posDataOutputLogic = posDataOutputLogic;
	}

	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

	public PosDataFormDto getPosDataFormDto() {
		return posDataFormDto;
	}

	public void setPosDataFormDto(PosDataFormDto posDataFormDto) {
		this.posDataFormDto = posDataFormDto;
	}

	public PosDataFormLogic getPosDataFormLogic() {
		return posDataFormLogic;
	}

	public void setPosDataFormLogic(PosDataFormLogic posDataFormLogic) {
		this.posDataFormLogic = posDataFormLogic;
	}

	/**
	 * @return クラス変数posDataZipOutputLogic を戻します。
	 */
	public ZipOutputLogic getPosDataZipOutputLogic() {
		return posDataZipOutputLogic;
	}

	/**
	 * @param posDataZipOutputLogic を クラス変数posDataZipOutputLogicへ設定します。
	 */
	public void setPosDataZipOutputLogic(ZipOutputLogic posDataZipOutputLogic) {
		this.posDataZipOutputLogic = posDataZipOutputLogic;
	}

	/**
	 * @return クラス変数posDataZipOnerCheck を戻します。
	 */
	public ZipOnerCheckLogic getPosDataZipOnerCheck() {
		return posDataZipOnerCheck;
	}

	/**
	 * @param posDataZipOnerCheck を クラス変数posDataZipOnerCheckへ設定します。
	 */
	public void setPosDataZipOnerCheck(ZipOnerCheckLogic posDataZipOnerCheck) {
		this.posDataZipOnerCheck = posDataZipOnerCheck;
	}
}