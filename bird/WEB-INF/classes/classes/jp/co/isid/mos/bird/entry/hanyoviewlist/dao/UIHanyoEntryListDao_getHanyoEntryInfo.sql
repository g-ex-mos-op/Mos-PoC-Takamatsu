select BT22.ENTRY_CD      
     , BT22.ENTRY_YEAR    
     , BT22.ENTRY_KAI     
     , BT22.ONER_CD   
     , BM11.ONER_NAME_KJ   
     , BM01.MISE_CD  
     , BM01.MISE_NAME_KJ    
     , BM12.STAFF_ID 
     , concat(BM12.STAFF_L_NAME_KJ , BM12.STAFF_F_NAME_KJ) as STAFF_NAME_KJ
     , (case when BM11.KEIYAKU_END < /*sysdate*/'20060601'
             then '1' else '0'
        end) as KEIYAKU_FLG

  from BT22ENKN BT22
     , BM12STAF BM12
     , BM11ONER BM11
     , BM01TENM BM01

 where BT22.ENTRY_CD = /*entryCd*/'01'
   and BT22.ENTRY_YEAR = /*entryYear*/'2006'
   and BT22.ENTRY_KAI = /*entryKai*/'020'
   and BM12.COMPANY_CD = BM11.COMPANY_CD
   and BM12.COMPANY_CD = BM01.COMPANY_CD
   and BT22.STAFF_ID = BM12.STAFF_ID
   and BM12.MISE_CD_1 = BM01.MISE_CD
   and BT22.ONER_CD = BM11.ONER_CD
   and BT22.ONER_CD = BM01.ONER_CD

 order by BT22.ONER_CD
        , BM01.MISE_CD
        , BM12.STAFF_ID