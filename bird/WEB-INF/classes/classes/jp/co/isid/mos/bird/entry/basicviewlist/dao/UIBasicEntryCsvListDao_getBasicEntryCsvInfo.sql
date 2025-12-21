select BT22.ENTRY_CD
     , BT22.ENTRY_YEAR
     , BT22.ENTRY_KAI
     , BT22.ONER_CD
     , BM11.ONER_NAME_KJ
     , BM01.SIBU_CD
     , BM10.SIBU_NAME
     , SUB1.SIBU_CD   as TORIKOMI_CD
     , SUB1.SIBU_NAME as TORIKOMI_NAME
     , BM12.MISE_CD_1 as MISE_CD
     , BM01.MISE_NAME_KJ
     , BT21SEKI.NAME as SEKININSHA_NAME
     , BT21SEKI.JOB_TYPE as SEKININSHA_JOB
     , BM12.STAFF_L_NAME_KJ as STAFF_SI_KJ
     , BM12.STAFF_F_NAME_KJ as STAFF_NA_KJ
     , BM12.STAFF_L_NAME_KNA as STAFF_SI_KNA
     , BM12.STAFF_F_NAME_KNA as STAFF_NA_KNA
     , (case when BM12.SEX in '0' then 'íj' else 'èó' end) as SEX 
     , BM12.BIRTHDAY
     , BM12.JOB
     , BT21SEKI.NAME as KEKKA_SAKI_NAME
     , rtrim(BT21SEKI.ZIP) as KEKKA_SAKI_ZIP
     , BT21SEKI.ADDRESS1 as KEKKA_SAKI_ADDRESS1
     , BT21SEKI.ADDRESS2 as KEKKA_SAKI_ADDRESS2
     , BT21SEKI.ADDRESS3 as KEKKA_SAKI_ADDRESS3
     , BT22.EMP_EXP_YEAR
     , BT22.EMP_EXP_MONTH
     , BT22.PA_EXP_YEAR
     , BT22.PA_EXP_MONTH
     , (case when BT22.GUIDE_KBN in 'KEKKA' then 'åãâ ïÒçêêÊ' else (case when BT22.GUIDE_KBN IN 'OTHER' THEN 'ÇªÇÃëº' ELSE BT22.GUIDE_KBN end)end) as GUIDE_KBN 
     , (case when BT22.GUIDE_KBN in 'KEKKA' then BT21SEKI.NAME else (case when BT22.GUIDE_KBN in 'OTHER' then BT22.GUIDE_NAME else (select MISE_NAME_KJ from BM01TENM where MISE_CD=BT22.GUIDE_KBN and COMPANY_CD='00') end)end) as GUIDE_NAME 
     , rtrim(BT22.GUIDE_ZIP) as GUIDE_ZIP
     , BT22.GUIDE_ADRS1
     , BT22.GUIDE_ADRS2
     , BT22.GUIDE_ADRS3 
     , (case when BT22.OTHER_FLG1 in '1' then 'óv' else 'ïsóv' end) as OTHER_FLG1
     , (case when BT22.OTHER_FLG2 in '1' then 'å§èCóæ' else (case when BT22.OTHER_FLG2 in '2' then 'é©ëÓ' else (case when BT22.OTHER_FLG2 in '3' then 'èhîëé{ê›' else '' end)end)end) as OTHER_FLG2
     , (case when BT22.OTHER_FLG3 in '1' then 'éÛÇØÇÈ' else 'éÛÇØÇ»Ç¢' end) as OTHER_FLG3
     , BT22.BOSS_NAME
     , BT22.BOSS_GROUP
     , BT22.BOSS_JOB_TYPE
     , BT22.BOSS_COMMENT
     , BT22.LAST_USER
     , BR01.USER_NAME_KJ as LAST_USER_NAME
     , BM11.KEIYAKU_END

  from BT22ENKN BT22
       left join (select ENTRY_CD
                       , ENTRY_YEAR
                       , ENTRY_KAI
                       , ONER_CD
                       , NAME
                       , JOB_TYPE
                       , ZIP
                       , ADDRESS1
                       , ADDRESS2
                       , ADDRESS3
                    from BT21ENOJ
                   where COMPANY_CD = '00'
                     and ATESAKI_KBN = '00') BT21SEKI
              on (BT22.ENTRY_CD   = BT21SEKI.ENTRY_CD   and
                  BT22.ENTRY_YEAR = BT21SEKI.ENTRY_YEAR and
                  BT22.ENTRY_KAI  = BT21SEKI.ENTRY_KAI  and
                  BT22.ONER_CD    = BT21SEKI.ONER_CD       )

       left join BR01USER BR01
              on (BT22.LAST_USER = BR01.USER_ID)

     , BM12STAF BM12
     , BM11ONER BM11
     , BM10GSIB BM10
     , BM01TENM BM01
       left join BM10GSIB SUB1 on (BM01.COMPANY_CD = SUB1.COMPANY_CD and BM01.AREA_DAI = SUB1.SIBU_CD)

 where BT22.ENTRY_CD   = /*entryCd*/'01'
   and BT22.ENTRY_YEAR = /*entryYear*/'2006'
   and BT22.ENTRY_KAI  = /*entryKai*/'15'
   and BT22.STAFF_ID   = BM12.STAFF_ID
   and BM12.MISE_CD_1  = BM01.MISE_CD
   and BM01.COMPANY_CD = BM10.COMPANY_CD
   and BM01.COMPANY_CD = '00'
   and BM01.SIBU_CD    = BM10.SIBU_CD
   and BT22.ONER_CD    = BM11.ONER_CD
   and BM11.COMPANY_CD = '00'

 order by BT22.ONER_CD
        , BM12.MISE_CD_1
