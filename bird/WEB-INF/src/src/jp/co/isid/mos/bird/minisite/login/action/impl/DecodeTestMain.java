package jp.co.isid.mos.bird.minisite.login.action.impl;

import jp.co.isid.mos.bird.common.util.BlowfishUtil;
import jp.co.isid.mos.bird.minisite.login.exception.MinisiteIllegalAccessException;

public class DecodeTestMain {
    /** 半角スペース */
    private static final String SPACE = " ";
    /** 半角プラス */
    private static final String PLUS = "+";
    /** 半角スラッシュ */
    private static final String SLASH = "/";

	public static void main(String[] args) {

		try {
			for( int i=0; i< args.length; i++) {
				String key = args[i];
				String strKey = key;
				try {
					try {
						if (key.contains(SPACE)) {
							//URLにある文字「+」はスペースに文字化けされたので、スペースを「+」に変換してから、Base64.getEncoderで複号する。
							strKey = key.replaceAll(SPACE, PLUS);
							key = BlowfishUtil.decryptNotUrl(BlowfishUtil.MINISITE_DECRYPT_KEY, strKey);
						} else if (key.contains(PLUS) || key.contains(SLASH)) {
							//URLには、「+」が存在する または 「/」が存在する場合、URL変換不要、Base64.getEncoderで複号する。
							key = BlowfishUtil.decryptNotUrl(BlowfishUtil.MINISITE_DECRYPT_KEY, strKey);
						} else {
							//URLには、「+」「/」が存在しない場合、Base64.getUrlEncoderで変換してから複号化する。
							key = BlowfishUtil.decryptUrl(BlowfishUtil.MINISITE_DECRYPT_KEY, strKey);
						}
				    } catch (java.lang.IllegalArgumentException e) {
						//Base64で複号できない場合、旧メソッド（URLDecoder＋BASE64Decoder）で複号する。
						key = BlowfishUtil.decrypt(BlowfishUtil.MINISITE_DECRYPT_KEY, strKey);
				    }
			    } catch (Exception e) {
			        	// Blowfishで復号できない
			        	throw new MinisiteIllegalAccessException("パラメータは認識できません。");
			    }
				System.out.println("key[" + String.valueOf(i) + "]=" + key);
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
