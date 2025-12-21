package jp.co.isid.mos.bird.portal.login.action.impl;

import jp.co.isid.mos.bird.common.util.BlowfishUtil;

public class DecodeChannelURLMain {

	public static void main(String[] args) throws Exception {

		try {
			for( int i=0; i< args.length; i++) {
				String key = args[i];
				int idx1 = key.indexOf("?k=");
				int idx2 = key.indexOf("&u=");
				int idx3 = key.indexOf("&g=");
				int idx4 = key.indexOf("&t=");
				int idx5 = key.indexOf("&c=");
				int idx6 = key.indexOf("&o=");
				String strUrl  = key.substring(0,      idx1);
				String strKey1 = key.substring(idx1+3, idx2);
				String strKey2 = key.substring(idx2+3, idx3);
				String strKey3 = key.substring(idx3+3, idx4);
				String strKey4 = key.substring(idx4+3, idx5);
				String strKey5 = key.substring(idx5+3, idx6);
				String strKey6 = key.substring(idx6+3);
				try {
					try {
						System.out.println("url=[" + strUrl + "]");
						key = BlowfishUtil.decrypt(BlowfishUtil.ENCRYPT_KEY, strKey1);
						System.out.println("k=[" + key + "]");
						key = BlowfishUtil.decrypt(BlowfishUtil.ENCRYPT_KEY, strKey2);
						System.out.println("u=[" + key + "]");
						key = BlowfishUtil.decrypt(BlowfishUtil.ENCRYPT_KEY, strKey3);
						System.out.println("g=[" + key + "]");
						key = BlowfishUtil.decrypt(BlowfishUtil.ENCRYPT_KEY, strKey4);
						System.out.println("t=[" + key + "]");
						key = BlowfishUtil.decrypt(BlowfishUtil.ENCRYPT_KEY, strKey5);
						System.out.println("c=[" + key + "]");
						key = BlowfishUtil.decrypt(BlowfishUtil.ENCRYPT_KEY, strKey6);
						System.out.println("o=[" + key + "]");
				    } catch (java.lang.IllegalArgumentException e) {
				    }
			    } catch (Exception e) {
			        	// Blowfishで復号できない
			        	throw new Exception("パラメータは認識できません。");
			    }
			}
		} catch (Exception e) {
        	// Blowfishで復号できない
        	throw new Exception("パラメータは認識できません。");
		}

	}
}
