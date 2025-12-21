select br01user.user_id 
      ,br01user.user_name_kj 
      ,br01user.user_name_kana 
      ,bm03uscp.r_company_cd 
      ,bc02comp.company_name 
      ,br01user.usertype_cd 
      ,bc04ustp.usertype_name
      ,bm11oner.oner_cd 
      ,bm11oner.oner_name_kj as sosiki 
from 
(((((((br01user inner join bm03uscp on br01user.user_id=bm03uscp.user_id )
          inner join bc02comp on bc02comp.r_company_cd=bm03uscp.r_company_cd)
          inner join bc04ustp on br01user.usertype_cd=bc04ustp.usertype_cd)
          inner join bm06uonr on br01user.user_id=bm06uonr.user_id)
          inner join bm11oner on bm06uonr.oner_cd=bm11oner.oner_cd and bm06uonr.company_cd=bm11oner.company_cd)
          inner join bc06comm on bm06uonr.company_cd=bc06comm.company_cd)
          inner join br04usrl on br04usrl.user_id = br01user.user_id)
          
where 
      bm03uscp.R_COMPANY_CD = /*R_COMPANY_CD*/'00000000000'
  AND bc06comm.R_COMPANY_CD = /*R_COMPANY_CD*/'00000000000'

/*IF STOP_FLG != null */
    AND br01user.stop_flg <> '1' 
/*END*/ 

/*IF USER_NAME_KJ != null */
    AND br01user.USER_NAME_KJ like /*USER_NAME_KJ*/''
/*END*/

/*IF ONER_CD != null */
    AND bm11oner.ONER_CD = /*ONER_CD*/'00000'
/*END*/

/*IF ONER_NAME_KJ != null */
    AND bm11oner.ONER_NAME_KJ like /*ONER_NAME_KJ*/'abc'
/*END*/

/*IF ROLE_CD != null */
    AND br04usrl.role_cd=/*ROLE_CD*/'000' 
/*END*/

/*IF userIdCond != null */
    AND br01user.user_id like /*userIdCond*/''
/*END*/

group by br01user.user_id 
        ,br01user.user_name_kj 
        ,br01user.user_name_kana 
        ,bm03uscp.r_company_cd 
        ,bc02comp.company_name 
        ,br01user.usertype_cd 
        ,bc04ustp.usertype_name 
        ,bm11oner.oner_cd 
        ,bm11oner.oner_name_kj 
order by user_id
