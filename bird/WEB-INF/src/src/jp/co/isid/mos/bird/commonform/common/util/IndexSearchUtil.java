package jp.co.isid.mos.bird.commonform.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 50‰¹ŒŸõ•¶š—ñæ“¾ƒNƒ‰ƒX
 * @author itamoto
 */
public final class IndexSearchUtil {

    /**
     * Constructor
     */
    private IndexSearchUtil() {
    }

    /**
     * 50‰¹ŒŸõ•¶š—ñæ“¾ˆ—
     * @param code
     * @return list
     */
    public static List getIndexSearchKeyList(int code) {
        return getIndexSearchKeyList(code, true, true);
    }
    /**
     * 50‰¹”š•¶š—ñƒŠƒXƒgæ“¾ˆ—
     * 
     * @return
     */
    public static List getListNum(boolean inZenkaku, boolean inHankaku) {

        List list = new ArrayList();
        for(int key=0; key<=9; key++) {
        	List listKey = getSearchKeyListNum(key, inZenkaku, inHankaku);
        	if(listKey != null && listKey.size()>0) {
        		Object keyInfo[] = new Object[2];
        		keyInfo[0]=new Integer(key);
        		keyInfo[1]=listKey;
        		list.add(keyInfo);
        	}
        }
        return list;
    }
    /**
     * 50‰¹ƒJƒ^ƒJƒi•¶š—ñƒŠƒXƒgæ“¾ˆ—
     * 
     * @return
     */
    public static List getListKana(boolean inZenkaku, boolean inHankaku) {

        List list = new ArrayList();
        for(int key=10; key<=100; key++) {
        	List listKey = getSearchKeyListKana(key, inZenkaku, inHankaku);
        	if(listKey != null && listKey.size()>0) {
        		Object keyInfo[] = new Object[2];
        		keyInfo[0]=new Integer(key);
        		keyInfo[1]=listKey;
        		list.add(keyInfo);
        	}
        }
        return list;
    }
    /**
     * 50‰¹ƒ[ƒ}š•¶š—ñƒŠƒXƒgæ“¾ˆ—
     * 
     * @return
     */
    public static List getListRoma(boolean inZenkaku, boolean inHankaku) {

        List list = new ArrayList();
        for(int key=101; key<=126; key++) {
        	List listKey = getSearchKeyListRoma(key, inZenkaku, inHankaku);
        	if(listKey != null && listKey.size()>0) {
        		Object keyInfo[] = new Object[2];
        		keyInfo[0]=new Integer(key);
        		keyInfo[1]=listKey;
        		list.add(keyInfo);
        	}
        }
        return list;
    }
    /**
     * 50‰¹‹L†•¶š—ñƒŠƒXƒgæ“¾ˆ—
     * 
     * @return
     */
    public static List getListKigo(boolean inZenkaku, boolean inHankaku) {

        List list = new ArrayList();
        for(int key=131; key<=135; key++) {
        	List listKey = getSearchKeyListKigo(key, inZenkaku, inHankaku);
        	if(listKey != null && listKey.size()>0) {
        		Object keyInfo[] = new Object[2];
        		keyInfo[0]=new Integer(key);
        		keyInfo[1]=listKey;
        		list.add(keyInfo);
        	}
        }
        return list;
    }
    /**
     * 
     * @param list
     * @param code
     * @param inZenkaku
     * @param inHankaku
     */
    private static List getSearchKeyListNum(int code, boolean inZenkaku, boolean inHankaku) {
        List list = new ArrayList();

        switch (code) {
        case 0:
            if(inZenkaku) {list.add("‚O");}
            if(inHankaku) {list.add("0");}
            break;
        case 1:
        	if(inZenkaku) {list.add("‚P");}
        	if(inHankaku) {list.add("1");}
            break;
        case 2:
        	if(inZenkaku) {list.add("‚Q");}
        	if(inHankaku) {list.add("2");}
            break;
        case 3:
        	if(inZenkaku) {list.add("‚R");}
        	if(inHankaku) {list.add("3");}
            break;
        case 4:
        	if(inZenkaku) {list.add("‚S");}
        	if(inHankaku) {list.add("4");}
            break;
        case 5:
        	if(inZenkaku) {list.add("‚T");}
        	if(inHankaku) {list.add("5");}
            break;
        case 6:
        	if(inZenkaku) {list.add("‚U");}
        	if(inHankaku) {list.add("6");}
            break;
        case 7:
        	if(inZenkaku) {list.add("‚V");}
        	if(inHankaku) {list.add("7");}
            break;
        case 8:
        	if(inZenkaku) {list.add("‚W");}
        	if(inHankaku) {list.add("8");}
            break;
        case 9:
        	if(inZenkaku) {list.add("‚X");}
        	if(inHankaku) {list.add("9");}
            break;
        default:
            break;        
        }
        return list;
    }
    /**
     * 
     * @param list
     * @param code
     * @param inZenkaku
     * @param inHankaku
     */
    private static List getSearchKeyListKana(int code, boolean inZenkaku, boolean inHankaku) {
        List list = new ArrayList(0);
        switch (code) {
        case 11:
        	if(inZenkaku) {
        		list.add("ƒA");
        		list.add("ƒ@");
        	}
        	if(inHankaku) {
        		list.add("±");
        	}
            break;
        case 12:
        	if(inZenkaku) {
        		list.add("ƒC");
        		list.add("ƒB");
        	}
        	if(inHankaku) {
        		list.add("²");
        	}
            break;
        case 13:
        	if(inZenkaku) {
        		list.add("ƒE");
        		list.add("ƒD");
        	}
        	if(inHankaku) {list.add("³");}
            break;
        case 14:
        	if(inZenkaku) {
        		list.add("ƒG");
        		list.add("ƒF");
        	}
        	if(inHankaku) {list.add("´");}
            break;
        case 15:
        	if(inZenkaku) {
        		list.add("ƒI");
        		list.add("ƒH");
        	}
        	if(inHankaku) {list.add("µ");}
            break;
        case 21:
        	if(inZenkaku) {
        		list.add("ƒJ");
            	list.add("ƒK");
            	list.add("ƒ•");
        	}
        	if(inHankaku) {list.add("¶");}
            break;
        case 22:
        	if(inZenkaku) {
        		list.add("ƒL");
        		list.add("ƒM");
        	}
        	if(inHankaku) {list.add("·");}
            break;
        case 23:
        	if(inZenkaku) {
        		list.add("ƒN");
        		list.add("ƒO");
        	}
        	if(inHankaku) {list.add("¸");}
            break;
        case 24:
        	if(inZenkaku) {
        		list.add("ƒP");
        		list.add("ƒQ");
        		list.add("ƒ–");
        	}
        	if(inHankaku) {list.add("¹");}
            break;
        case 25:
        	if(inZenkaku) {
	            list.add("ƒR");
	            list.add("ƒS");
        	}
        	if(inHankaku) {list.add("º");}
            break;
        case 31:
        	if(inZenkaku) {
	            list.add("ƒT");
	            list.add("ƒU");
        	}
        	if(inHankaku) {list.add("»");}
            break;
        case 32:
        	if(inZenkaku) {
	            list.add("ƒV");
	            list.add("ƒW");
        	}
        	if(inHankaku) {list.add("¼");}
            break;
        case 33:
        	if(inZenkaku) {
	            list.add("ƒX");
	            list.add("ƒY");
        	}
        	if(inHankaku) {list.add("½");}
            break;
        case 34:
        	if(inZenkaku) {
	            list.add("ƒZ");
	            list.add("ƒ[");
        	}
        	if(inHankaku) {list.add("¾");}
            break;
        case 35:
        	if(inZenkaku) {
	            list.add("ƒ\");
	            list.add("ƒ]");
        	}
        	if(inHankaku) {list.add("¿");}
            break;
        case 41:
        	if(inZenkaku) {
	            list.add("ƒ^");
	            list.add("ƒ_");
        	}
        	if(inHankaku) {list.add("À");}
            break;
        case 42:
        	if(inZenkaku) {
	            list.add("ƒ`");
	            list.add("ƒa");
        	}
        	if(inHankaku) {list.add("Á");}
            break;
        case 43:
        	if(inZenkaku) {
	            list.add("ƒc");
	            list.add("ƒd");
	            list.add("ƒb");
        	}
        	if(inHankaku) {list.add("Â");}
            break;
        case 44:
        	if(inZenkaku) {
	            list.add("ƒe");
	            list.add("ƒf");
        	}
        	if(inHankaku) {list.add("Ã");}
            break;
        case 45:
        	if(inZenkaku) {
	            list.add("ƒg");
	            list.add("ƒh");
        	}
        	if(inHankaku) {list.add("Ä");}
            break;
        case 51:
        	if(inZenkaku) {
        		list.add("ƒi");
        	}
        	if(inHankaku) {list.add("Å");}
            break;
        case 52:
        	if(inZenkaku) {
	            list.add("ƒj");
        	}
        	if(inHankaku) {list.add("Æ");}
            break;
        case 53:
        	if(inZenkaku) {
	            list.add("ƒk");
        	}
        	if(inHankaku) {list.add("Ç");}
            break;
        case 54:
        	if(inZenkaku) {
        		list.add("ƒl");
        	}
        	if(inHankaku) {list.add("È");}
            break;
        case 55:
        	if(inZenkaku) {
        		list.add("ƒm");
        	}
        	if(inHankaku) {list.add("É");}
            break;
        case 61:
        	if(inZenkaku) {
        		list.add("ƒn");
	            list.add("ƒo");
	            list.add("ƒp");
        	}
        	if(inHankaku) {list.add("Ê");}
            break;
        case 62:
        	if(inZenkaku) {
        		list.add("ƒq");  // xtsone 2006/10/12 "ƒn"‚É‚È‚Á‚Ä‚¢‚½‚Ì‚ğC³
	            list.add("ƒr");
	            list.add("ƒs");
        	}
        	if(inHankaku) {list.add("Ë");}
            break;
        case 63:
        	if(inZenkaku) {
        		list.add("ƒt");
	            list.add("ƒu");
	            list.add("ƒv");
        	}
        	if(inHankaku) {list.add("Ì");}
            break;
        case 64:
        	if(inZenkaku) {
        		list.add("ƒw");
	            list.add("ƒx");
	            list.add("ƒy");
        	}
        	if(inHankaku) {list.add("Í");}
            break;
        case 65:
        	if(inZenkaku) {
	            list.add("ƒz");
	            list.add("ƒ{");
	            list.add("ƒ|");
        	}
        	if(inHankaku) {list.add("Î");}
            break;
        case 71:
        	if(inZenkaku) {
        		list.add("ƒ}");
	        }
	    	if(inHankaku) {list.add("Ï");}
            break;
        case 72:
        	if(inZenkaku) {
	            list.add("ƒ~");
		    }
			if(inHankaku) {list.add("Ğ");}
            break;
        case 73:
        	if(inZenkaku) {
	            list.add("ƒ€");
			}
			if(inHankaku) {list.add("Ñ");}
            break;
        case 74:
        	if(inZenkaku) {
	            list.add("ƒ");
	        }
	    	if(inHankaku) {list.add("Ò");}
            break;
        case 75:
        	if(inZenkaku) {
	            list.add("ƒ‚");
        	}
	    	if(inHankaku) {list.add("Ó");}
            break;
        case 81:
        	if(inZenkaku) {
	            list.add("ƒ„");
	            list.add("ƒƒ");
        	}
	    	if(inHankaku) {list.add("Ô");}
            break;
        case 82:
        	if(inZenkaku) {
	            list.add("ƒ†");
	            list.add("ƒ…");
        	}
	    	if(inHankaku) {list.add("Õ");}
            break;
        case 83:
        	if(inZenkaku) {
	            list.add("ƒˆ");
	            list.add("ƒ‡");
        	}
	    	if(inHankaku) {list.add("Ö");}
            break;
        case 91:
        	if(inZenkaku) {
        		list.add("ƒ‰");
        	}
	    	if(inHankaku) {list.add("×");}
            break;
        case 92:
        	if(inZenkaku) {
        		list.add("ƒŠ");
        	}
	    	if(inHankaku) {list.add("Ø");}
            break;
        case 93:
        	if(inZenkaku) {
        		list.add("ƒ‹");
        	}
	    	if(inHankaku) {list.add("Ù");}
            break;
        case 94:
        	if(inZenkaku) {
        		list.add("ƒŒ");
        	}
	    	if(inHankaku) {list.add("Ú");}
            break;
        case 95:
        	if(inZenkaku) {
        		list.add("ƒ");
        	}
	    	if(inHankaku) {list.add("Û");}
            break;
        case 96:
        	if(inZenkaku) {
	            list.add("ƒ");
	            list.add("ƒ");
        	}
	    	if(inHankaku) {list.add("Ü");}
            break;
        case 97:
        	if(inZenkaku) {
        		list.add("ƒ’");
        	}
	    	if(inHankaku) {list.add("¦");}
            break;
        case 98:
        	if(inZenkaku) {
        		list.add("ƒ“");
        	}
	    	if(inHankaku) {list.add("İ");}
            break;
        default:
            break;        
        }
        return list;
    }
    /**
     * 
     * @param code
     * @param inZenkaku
     * @param inHankaku
     * @return
     */
    private static List getSearchKeyListRoma(int code, boolean inZenkaku, boolean inHankaku) {
        List list = new ArrayList(0);
        switch (code) {
        case 101:
        	if(inZenkaku) {
	            list.add("‚`");
	            list.add("‚");
        	}
	    	if(inHankaku) {
	    		list.add("A");
	            list.add("a");
            }
            break;
        case 102:
        	if(inZenkaku) {
	            list.add("‚a");
	            list.add("‚‚");
	        }
	    	if(inHankaku) {
	    		list.add("B");
	    		list.add("b");
            }
            break;
        case 103:
        	if(inZenkaku) {
	            list.add("‚b");
	            list.add("‚ƒ");
        	}
		    if(inHankaku) {
	            list.add("C");
	            list.add("c");
        	}
        	break;
        case 104:
        	if(inZenkaku) {
	            list.add("‚c");
	            list.add("‚„");
	        }
	    	if(inHankaku) {
	            list.add("D");
	            list.add("d");
	        }
            break;
        case 105:
        	if(inZenkaku) {
	            list.add("‚d");
	            list.add("‚…");
	        }
	    	if(inHankaku) {
	            list.add("E");
	            list.add("e");
	        }
            break;
        case 106:
        	if(inZenkaku) {
	            list.add("‚e");
	            list.add("‚†");
	        }
	    	if(inHankaku) {
	            list.add("F");
	            list.add("f");
	        }
            break;
        case 107:
        	if(inZenkaku) {
	            list.add("‚f");
	            list.add("‚‡");
	        }
	    	if(inHankaku) {
	            list.add("G");
	            list.add("g");
	        }
            break;
        case 108:
        	if(inZenkaku) {
	            list.add("‚g");
	            list.add("‚ˆ");
	        }
	    	if(inHankaku) {
	            list.add("H");
	            list.add("h");
	        }
            break;
        case 109:
        	if(inZenkaku) {
	            list.add("‚h");
	            list.add("‚‰");
	        }
	    	if(inHankaku) {
	            list.add("I");
	            list.add("i");
	        }
            break;
        case 110:
        	if(inZenkaku) {
	            list.add("‚i");
	            list.add("‚Š");
	        }
	    	if(inHankaku) {
	            list.add("J");
	            list.add("j");
	        }
            break;
        case 111:
        	if(inZenkaku) {
	            list.add("‚j");
	            list.add("‚‹");
	        }
	    	if(inHankaku) {
	            list.add("K");
	            list.add("k");
	        }
            break;
        case 112:
        	if(inZenkaku) {
	            list.add("‚k");
	            list.add("‚Œ");
	        }
	    	if(inHankaku) {
	            list.add("L");
	            list.add("l");
	        }
            break;
        case 113:
        	if(inZenkaku) {
	            list.add("‚l");
	            list.add("‚");
	        }
	    	if(inHankaku) {
	            list.add("M");
	            list.add("m");
	        }
            break;
        case 114:
        	if(inZenkaku) {
	            list.add("‚m");
	            list.add("‚");
	        }
	    	if(inHankaku) {
	            list.add("N");
	            list.add("n");
	        }
            break;
        case 115:
        	if(inZenkaku) {
	            list.add("‚n");
	            list.add("‚");
	        }
	    	if(inHankaku) {
	            list.add("O");
	            list.add("o");
	        }
            break;
        case 116:
        	if(inZenkaku) {
	            list.add("‚o");
	            list.add("‚");
	        }
	    	if(inHankaku) {
	            list.add("P");
	            list.add("p");
	        }
            break;
        case 117:
        	if(inZenkaku) {
	            list.add("‚p");
	            list.add("‚‘");
	        }
	    	if(inHankaku) {
	            list.add("Q");
	            list.add("q");
	        }
            break;
        case 118:
        	if(inZenkaku) {
	            list.add("‚q");
	            list.add("‚’");
	        }
	    	if(inHankaku) {
	            list.add("R");
	            list.add("r");
	        }
            break;
        case 119:
        	if(inZenkaku) {
	            list.add("‚r");
	            list.add("‚“");
	        }
	    	if(inHankaku) {
	            list.add("S");
	            list.add("s");
	        }
            break;
        case 120:
        	if(inZenkaku) {
	            list.add("‚s");
	            list.add("‚”");
	        }
	    	if(inHankaku) {
	            list.add("T");
	            list.add("t");
	        }
            break;
        case 121:
        	if(inZenkaku) {
	            list.add("‚t");
	            list.add("‚•");
	        }
	    	if(inHankaku) {
	            list.add("U");
	            list.add("u");
	        }
            break;
        case 122:
        	if(inZenkaku) {
	            list.add("‚u");
	            list.add("‚–");
	        }
	    	if(inHankaku) {
	            list.add("V");
	            list.add("v");
	        }
            break;
        case 123:
        	if(inZenkaku) {
	            list.add("‚v");
	            list.add("‚—");
	        }
	    	if(inHankaku) {
	            list.add("W");
	            list.add("w");
	        }
            break;
        case 124:
        	if(inZenkaku) {
	            list.add("‚w");
	            list.add("‚˜");
	        }
	    	if(inHankaku) {
	            list.add("X");
	            list.add("x");
	        }
            break;
        case 125:
        	if(inZenkaku) {
	            list.add("‚x");
	            list.add("‚™");
	        }
	    	if(inHankaku) {
	            list.add("Y");
	            list.add("y");
	        }
            break;
        case 126:
        	if(inZenkaku) {
	            list.add("‚y");
	            list.add("‚š");
	        }
	    	if(inHankaku) {
	            list.add("Z");
	            list.add("z");
	        }
            break;
        default:
            break;        
        }
        return list;
    }
    /**
     * ‹L†ƒŠƒXƒgæ“¾ˆ—
     * 
     * @param list
     * @param code
     * @param inZenkaku
     * @param inHankaku
     */
    private static List getSearchKeyListKigo(int code, boolean inZenkaku, boolean inHankaku) {
        List list = new ArrayList();

        switch (code) {
        case 131:
            if(inZenkaku) {list.add("•");}
            if(inHankaku) {list.add("&");}
            break;
        default:
            break;        
        }
        return list;
    }

    /**
     * 
     * @param list
     * @param code
     * @param inZenkaku
     * @param inHankaku
     */
    private static List getIndexSearchKeyList(int code, boolean inZenkaku, boolean inHankaku) {
        List list = getSearchKeyListNum(code, inZenkaku, inHankaku);
        if(list.size()>0) {
        	return list;
        }
        list = getSearchKeyListKana(code, inZenkaku, inHankaku);
        if(list.size()>0) {
        	return list;
        }
        list = getSearchKeyListRoma(code, inZenkaku, inHankaku);
        if(list.size()>0) {
        	return list;
        }
        list = getSearchKeyListKigo(code, inZenkaku, inHankaku);
        return list;
    }
}
