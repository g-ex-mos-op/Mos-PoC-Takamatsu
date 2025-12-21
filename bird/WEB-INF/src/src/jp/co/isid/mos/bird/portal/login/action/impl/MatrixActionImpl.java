package jp.co.isid.mos.bird.portal.login.action.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.portal.login.action.MatrixAction;
import jp.co.isid.mos.bird.portal.login.dto.MatrixDto;
import jp.co.isid.mos.bird.portal.login.entity.UIUserMatrixInfo;
import jp.co.isid.mos.bird.portal.login.logic.CheckInputMatrixInfoLogic;
import jp.co.isid.mos.bird.portal.login.logic.GetMatrixInfoLogic;
import jp.co.isid.mos.bird.portal.login.logic.MakeMatrixLogic;


/**
 * マトリクス認証用Action
 * 2006/02/08 セッションエラー時に元の画面へ戻る処理を追加
 * @author xnkusama
 *
 */
public class MatrixActionImpl implements MatrixAction {

    /* アクションID */
    public static final String init_ACTION_ID = "BPO000A05";
    public static final String back_ACTION_ID = "BPO000A06";
    public static final String matrix_ACTION_ID = "BPO000A07";
    
    /* DTO */
	private MatrixDto _matrixDto;
    /* BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    private HttpSession session;
    /* LOGIC */
    private CheckInputMatrixInfoLogic checkMatrixInfoLogicImpl;
    private MakeMatrixLogic makeMatrixLogic;
    private GetMatrixInfoLogic getMatrixInfoLogicImpl;
    
    
    /**
     * DTOを取得します
     * @param matrixDto
     */
    public void setMatrixDto(final MatrixDto matrixDto) {
        this._matrixDto = matrixDto;
    }
    /**
     * DTOを設定します
     * @return
     */
	private MatrixDto getMatrixDto() {
	    return this._matrixDto;
    }
    
    /**
     * BIRDユーザー情報を設定します
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * BIRDユーザー情報を取得します
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    
    /**
     * ５×５の2桁１6進数を生成し、専用DTOへセット
     * @return
     */
	public String init() {
//		S2Container container = null;
		try {
//            container = SingletonS2ContainerFactory.getContainer();
//			
//			MakeMatrixLogicImpl logic = (MakeMatrixLogicImpl) container.getComponent(MakeMatrixLogicImpl.class);
            
			List data = getMakeMatrixLogic().makeMatrixKey();
            getMatrixDto().setMatrixList(data);
            getMatrixDto().setMatrixValue(""); //2008/11/13 add
            getMatrixDto().setViewValue("");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
    
    /**
     * マトリクス認証実行
     * @return
     */
    public String matrix() throws ApplicationException {
//        S2Container container = SingletonS2ContainerFactory.getContainer();
        
        // マトリクス認証情報 取得
        //   ログインDTO作成
        LoginDto loginDto = new LoginDto();
        loginDto.setUserId(getBirdUserInfo().getUserID());
        //   入力チェック
        getCheckMatrixInfoLogicImpl().check(getMatrixDto(), loginDto);

         /* セッションエラー時にログインへ遷移してきた場合は、元の画面へ戻る */
        if (session.getAttribute("NoSessionReturnViewID") != null) {
            String returnViewId = (String) session.getAttribute("NoSessionReturnViewID");
            if (!"".equals(returnViewId.trim())) {
            	//パスワード有効期限チェック実行判断フラグをfalseに設定します。
                getMatrixDto().setPswdUpdtChekFlg(false);
                session.setAttribute("NoSessionReturnViewID", "");
                return returnViewId;
            }
        }
        UIUserMatrixInfo uiUserMatrixInfo = getGetMatrixInfoLogicImpl().getMatrixInfoLogic(loginDto);
        if (!uiUserMatrixInfo.getLastUser().equals(getBirdUserInfo().getUserID())) {
            //マトリクスリセットフラグON
            getMatrixDto().setMatrixResetFlg(true);
            return "BPO000V03";
        }
//--- 2006/02/08 add end
        return "BPO001V01";
    }
    
    /**
     * マトリクス認証画面 戻るアクション
     * @return
     */
    public String back() {
        return "BPO000V01";
    }
    
    public void setSession(HttpSession session) {
        this.session = session;
    }
    public CheckInputMatrixInfoLogic getCheckMatrixInfoLogicImpl() {
        return checkMatrixInfoLogicImpl;
    }
    public void setCheckMatrixInfoLogicImpl(
            CheckInputMatrixInfoLogic checkMatrixInfoLogicImpl) {
        this.checkMatrixInfoLogicImpl = checkMatrixInfoLogicImpl;
    }
    public MakeMatrixLogic getMakeMatrixLogic() {
        return makeMatrixLogic;
    }
    public void setMakeMatrixLogic(MakeMatrixLogic makeMatrixLogic) {
        this.makeMatrixLogic = makeMatrixLogic;
    }
    public GetMatrixInfoLogic getGetMatrixInfoLogicImpl() {
        return getMatrixInfoLogicImpl;
    }
    public void setGetMatrixInfoLogicImpl(GetMatrixInfoLogic getMatrixInfoLogicImpl) {
        this.getMatrixInfoLogicImpl = getMatrixInfoLogicImpl;
    }
}