select
    BT23.ENTRY_CD,
    BT23.ENTRY_YEAR,
    BT23.ENTRY_KAI,
    BT23.STAFF_ID,
    BR18.FROM_DT,
    KEKKA_TOUROKU.TO_DT,
    BR17.ENTRY_TITLE
from
    BT23MLEJ as BT23
    inner join BR17ENTL as BR17 on BT23.ENTRY_CD = BR17.ENTRY_CD     and
                                   BT23.ENTRY_YEAR = BR17.ENTRY_YEAR and
                                   BT23.ENTRY_KAI = BR17.ENTRY_KAI   
    inner join BR18ENTD as BR18 on BT23.ENTRY_CD = BR18.ENTRY_CD     and
                                   BT23.ENTRY_YEAR = BR18.ENTRY_YEAR and
                                   BT23.ENTRY_KAI = BR18.ENTRY_KAI   and
                                   BR18.USERTYPE_CD = '01'           and
                                   BR18.DAY_KBN = '04'   
    inner join BR18ENTD as KEKKA_TOUROKU on BT23.ENTRY_CD = KEKKA_TOUROKU.ENTRY_CD     and
    		                                BT23.ENTRY_YEAR = KEKKA_TOUROKU.ENTRY_YEAR and
	   	                                    BT23.ENTRY_KAI = KEKKA_TOUROKU.ENTRY_KAI   and
		                                    KEKKA_TOUROKU.USERTYPE_CD = '01'           and
		                                    KEKKA_TOUROKU.DAY_KBN = '06'
where
    BT23.STAFF_ID = /*staffId*/'98000325' and
    BR18.FROM_DT <= /*sysDate*/'20070120' and 
    KEKKA_TOUROKU.TO_DT >= /*sysDate*/'20070120'
