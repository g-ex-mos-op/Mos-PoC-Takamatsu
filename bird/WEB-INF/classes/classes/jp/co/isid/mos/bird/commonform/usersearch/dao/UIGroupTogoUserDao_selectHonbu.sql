select br01user.user_id 
      ,br01user.user_name_kj 
      ,br01user.user_name_kana 
      ,bm03uscp.r_company_cd 
      ,bc02comp.company_name 
      ,br01user.bumon_cd 
      ,vbr01.bumon_name as sosiki
      ,br01user.usertype_cd 
      ,bc04ustp.usertype_name 
from 
(((((br01user inner join bm03uscp on br01user.user_id=bm03uscp.user_id )
          inner join bc02comp on bc02comp.r_company_cd=bm03uscp.r_company_cd)
          inner join bc04ustp on br01user.usertype_cd=bc04ustp.usertype_cd)
          inner join VBR01USER_BMON vbr01 on br01user.user_id=vbr01.user_id and bm03uscp.r_company_cd=vbr01.r_company_cd)
          inner join br04usrl on br04usrl.user_id = br01user.user_id)
where 
    bm03uscp.R_COMPANY_CD = /*R_COMPANY_CD*/'00'

/*IF STOP_FLG != null */
    AND br01user.stop_flg <> '1' 
/*END*/ 

/*IF USER_NAME_KJ != null */
    AND br01user.USER_NAME_KJ like /*USER_NAME_KJ*/''
/*END*/

/*IF BUMON_CD != null */
    AND br01user.BUMON_CD = /*BUMON_CD*/'00000000'
/*END*/

/*IF ROLE_CD != null */
    AND br04usrl.ROLE_CD = /*ROLE_CD*/'000'
/*END*/

/*IF userIdCond != null */
    AND br01user.user_id like /*userIdCond*/''
/*END*/

group by br01user.user_id 
        ,br01user.user_name_kj 
        ,br01user.user_name_kana 
        ,bm03uscp.r_company_cd 
        ,bc02comp.company_name 
        ,br01user.bumon_cd 
        ,vbr01.bumon_name 
        ,br01user.usertype_cd 
        ,bc04ustp.usertype_name 
order by user_id
