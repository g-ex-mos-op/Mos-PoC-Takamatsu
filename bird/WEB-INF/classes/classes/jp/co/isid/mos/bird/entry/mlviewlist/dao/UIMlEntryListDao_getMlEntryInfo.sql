select BT23.ENTRY_CD
     , BT23.ENTRY_YEAR
     , BT23.ENTRY_KAI
     , BT23.ONER_CD
     , rtrim(BM11.ONER_NAME_KJ) as ONER_NAME_KJ
     , BM01.MISE_CD
     , rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ
     , BT23.EXAM_NO
     , rtrim(BR20.ENTRY_PLACE_NAME) as ENTRY_PLACE_NAME
     , BT23.STAFF_ID 
     , concat(rtrim(BM12.STAFF_L_NAME_KJ) , rtrim(BM12.STAFF_F_NAME_KJ)) as STAFF_NAME_KJ
     , BT23.ABILITY_CHK
     , BT23.EXAM_CHK
     , BT23.INTERVIEW_CHK
     , coalesce(VBT32.COURSE_STATUS, '') as COURSE_STATUS

  from BM11ONER BM11
     , BM01TENM BM01
     , BT23MLEJ BT23
       left outer join VBT32MLKR as VBT32 on VBT32.TOTAL_LAST_YEAR = BT23.ENTRY_YEAR and VBT32.TOTAL_LAST_KAI = BT23.ENTRY_KAI and VBT32.STAFF_ID = BT23.STAFF_ID
     , BR20MLPL BR20
     , BM12STAF BM12

 where BT23.ENTRY_CD       = /*entryCd*/'10'
   and BT23.ENTRY_YEAR     = /*entryYear*/'2006'
   and BT23.ENTRY_KAI      = /*entryKai*/'001'
   and BM12.SITUATION_KBN  <> '2'

   and BT23.COMPANY_CD     = BM11.COMPANY_CD
   and BT23.ONER_CD        = BM11.ONER_CD
   and BM11.COMPANY_CD     = BM01.COMPANY_CD
   and BM12.STAFF_ID       = BT23.STAFF_ID
   and BM12.MISE_CD_1      = BM01.MISE_CD
   and BT23.ENTRY_CD       = BR20.ENTRY_CD
   and BT23.ENTRY_YEAR     = BR20.ENTRY_YEAR
   and BT23.ENTRY_KAI      = BR20.ENTRY_KAI
   and BT23.ENTRY_PLACE_CD = BR20.ENTRY_PLACE_CD

/*IF searchSelectFlg.equals("0") */
   and BM01.AREA_DAI = /*sibuCd*/'011'
/*END*/        
/*IF searchSelectFlg.equals("1") */
   and BT23.ONER_CD = /*onerCd*/'36066'
/*END*/
/*IF searchSelectFlg.equals("2") */
   and BM01.MISE_CD = /*miseCd*/'00166'
/*END*/

 order by BM11.ONER_NAME_KJ
        , BM01.MISE_NAME_KJ
