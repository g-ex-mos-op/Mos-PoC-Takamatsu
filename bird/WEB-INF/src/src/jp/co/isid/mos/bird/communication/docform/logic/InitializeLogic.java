package jp.co.isid.mos.bird.communication.docform.logic;

import jp.co.isid.mos.bird.communication.docform.dto.DocFormDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
/**
 * 文書・フォーム画面
 * 
 * 初期化処理ロジックインターフェース
 * 
 * 作成日:2011/02/22
 * @author xkinu
 *
 */
public interface InitializeLogic {

    /**
     * 実行処理
     * 
     * ・自画面DTOの初期化
     * ・自分に公開されているカテゴリーをを取得する
     * 
     * @param birdDateInfo
     * @param birdUserInfo
     * @param sessionDto
     */
    public void execute(
    		  BirdDateInfo birdDateInfo
    		, BirdUserInfo birdUserInfo
    		, DocFormDto sessionDto);

}