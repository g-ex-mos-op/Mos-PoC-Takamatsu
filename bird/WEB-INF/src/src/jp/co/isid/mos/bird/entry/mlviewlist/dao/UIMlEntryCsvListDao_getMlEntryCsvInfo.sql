select BM11.ONER_CD
     , rtrim(BM11.ONER_NAME_KJ) as ONER_NAME_KJ
     , BM10.SIBU_CD
     , rtrim(BM10.SIBU_NAME) as SIBU_NAME
     , BM01.MISE_CD
     , rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ
     , rtrim(BC10.ENTRY_NAME) as ENTRY_NAME
     , coalesce(RIREKI.TOTAL_LAST_YEAR, '') as TOTAL_LAST_YEAR
     , coalesce(RIREKI.TOTAL_LAST_KAI, '') as TOTAL_LAST_KAI
     , VBT32.ENTRY_COUNT
     , BT23.EXAM_NO
     , rtrim(BM12.JOB) as JOB
     , rtrim(TANTO.NAME) as NAME_TANTO
     , rtrim(TANTO.TEL) as TEL_TANTO
     , rtrim(TANTO.ZIP) as ZIP_TANTO
     , rtrim(TANTO.ADDRESS1) as ADDRESS_TANTO1
     , rtrim(TANTO.ADDRESS2) as ADDRESS_TANTO2
     , rtrim(TANTO.ADDRESS3) as ADDRESS_TANTO3
     , rtrim(TANTO.SOUFU_NAME) as SOUFU_NAME_TANTO
     , rtrim(HOKOKU.NAME) as NAME_HOKOKU
     , rtrim(HOKOKU.TEL) as TEL_HOKOKU
     , rtrim(HOKOKU.ZIP) as ZIP_HOKOKU
     , rtrim(HOKOKU.ADDRESS1) as ADDRESS_HOKOKU1
     , rtrim(HOKOKU.ADDRESS2) as ADDRESS_HOKOKU2
     , rtrim(HOKOKU.ADDRESS3) as ADDRESS_HOKOKU3
     , rtrim(HOKOKU.SOUFU_NAME) as SOUFU_NAME_HOKOKU
     , rtrim(BM12.STAFF_L_NAME_KJ) as STAFF_L_NAME_KJ
     , rtrim(BM12.STAFF_F_NAME_KJ) as STAFF_F_NAME_KJ
     , rtrim(BM12.STAFF_L_NAME_KNA) as STAFF_L_NAME_KNA
     , rtrim(BM12.STAFF_F_NAME_KNA) as STAFF_F_NAME_KNA
     , (case BM12.SEX when '0' then 'íj' when '1' then 'èó' else '' end) as SEX 
     , rtrim(BM12.BIRTHDAY) as BIRTHDAY
     , BT23.EMP_EXP_YEAR
     , BT23.EMP_EXP_MONTH
     , BT23.PA_EXP_YEAR
     , BT23.PA_EXP_MONTH
     , coalesce(VBT32.COURSE_STATUS, '') as COURSE_STATUS
     , coalesce(VBT32.COMPLE_COURSE_DT, '') as COMPLE_COURSE_DT
     , coalesce(VBT32.COMPLE_COURSE_CD, '') as COMPLE_COURSE_CD
     , coalesce(VBT32.COMPLE_COURSE_NAME, '') as COMPLE_COURSE_NAME
     , BT23.ENTRY_PLACE_CD
     , rtrim(BT23.NOTE) as NOTE
     , BT23.ABILITY_CHK
     , BT23.EXAM_CHK
     , BT23.INTERVIEW_CHK
     , BT23.LAST_USER
     , BT23.LAST_TMSP
     , BM11.KEIYAKU_END

     , BT23.ENTRY_CD
     , BT23.ENTRY_YEAR
     , BT23.ENTRY_KAI
     , BT23.COMPANY_CD
     , BT23.ONER_CD

  from BM12STAF BM12
     , BM11ONER BM11
     , BM10GSIB BM10
     , BM01TENM BM01
     , BC10ENTM BC10
     , VBT32MLKR VBT32
     , BT23MLEJ BT23
       left outer join BT21ENOJ as TANTO on
               BT23.ENTRY_CD    = TANTO.ENTRY_CD
           and BT23.ENTRY_YEAR  = TANTO.ENTRY_YEAR
           and BT23.ENTRY_KAI   = TANTO.ENTRY_KAI
           and BT23.COMPANY_CD  = TANTO.COMPANY_CD
           and BT23.ONER_CD     = TANTO.ONER_CD
           and TANTO.ATESAKI_KBN ='02'
       left outer join BT21ENOJ as HOKOKU on
               BT23.ENTRY_CD    = HOKOKU.ENTRY_CD
           and BT23.ENTRY_YEAR  = HOKOKU.ENTRY_YEAR
           and BT23.ENTRY_KAI   = HOKOKU.ENTRY_KAI
           and BT23.COMPANY_CD  = HOKOKU.COMPANY_CD
           and BT23.ONER_CD     = HOKOKU.ONER_CD
           and HOKOKU.ATESAKI_KBN ='03'

left join 
      ( select BT32.TOTAL_LAST_YEAR
             , BT32.TOTAL_LAST_KAI
             , BT32.STAFF_ID as STAFF_ID
        
          from BT23MLEJ BT23a
             , BT32MLKR BT32
        
         where BT23a.ENTRY_CD   = /*entryCd*/'10'
           and BT23a.ENTRY_YEAR = /*entryYear*/'2006'
           and BT23a.ENTRY_KAI  = /*entryKai*/'001'
           and BT23a.STAFF_ID   = BT32.STAFF_ID
           and BT32.REENTRY_FLG = '0' 
           and BT32.REENTRY_BASE_YEAR in /*listReEntryBaseYear*/('2005','2006')) RIREKI
    on RIREKI.STAFF_ID = BT23.STAFF_ID


 where BT23.ENTRY_CD   = /*entryCd*/'10'
   and BT23.ENTRY_YEAR = /*entryYear*/'2006'
   and BT23.ENTRY_KAI  = /*entryKai*/'001'
   and BT23.STAFF_ID   = BM12.STAFF_ID
   and BT23.STAFF_ID   = VBT32.STAFF_ID
   and BT23.ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR
   and BT23.ENTRY_KAI  = VBT32.TOTAL_LAST_KAI
   and BT23.ENTRY_CD   = BC10.ENTRY_CD
   and BT23.COMPANY_CD = BM11.COMPANY_CD
   and BT23.ONER_CD    = BM11.ONER_CD
   and BM11.COMPANY_CD = BM01.COMPANY_CD
   and BM12.MISE_CD_1  = BM01.MISE_CD
   and BM01.COMPANY_CD = BM10.COMPANY_CD
   and BM01.AREA_DAI   = BM10.SIBU_CD
   and BM12.SITUATION_KBN  <> '2'

/*IF searchSelectFlg.equals("0") */
   and BM01.AREA_DAI = /*sibuCd*/'011'
/*END*/
/*IF searchSelectFlg.equals("1") */
   and BT23.ONER_CD = /*onerCd*/'00000'
/*END*/
/*IF searchSelectFlg.equals("2") */
   and BM01.MISE_CD = /*miseCd*/'00000'
/*END*/

 order by BM11.ONER_NAME_KJ
        , BM10.SIBU_NAME
        , BM01.MISE_NAME_KJ

