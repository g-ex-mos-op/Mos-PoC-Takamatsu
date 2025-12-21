select sub.* 
from 
(
select row_number() over(order by BT.reg_date desc, BT.seq desc) as row_num
      ,BT.reg_date REG_DATE
      ,BT.seq SEQ
      ,BT.cate_id CATE_ID
      ,rtrim(bm02.cate_name) CATE_NAME
      ,rtrim(BT.title) TITLE
      ,rtrim(BT.sub_title) SUB_TITLE
      ,BT.pub_date PUB_DATE
      ,BT.pub_user PUB_USER
      ,BR01.user_name_kj USER_NAME_KJ 
      ,BT.pub_org PUB_ORG
      ,rtrim(BT.pub_org_name) PUB_ORG_NAME
      ,BT.r_company_cd R_COMPANY_CD 
      ,rtrim(BT.kanri_no) KANRI_NO
      ,rtrim(BT.file_name) FILE_NAME
      ,rtrim(BT.attach_name1) ATTACH_NAME1
      ,rtrim(BT.attach_name2) ATTACH_NAME2
      ,rtrim(BT.attach_name3) ATTACH_NAME3
      ,rtrim(BT.attach_fl1) ATTACH_FL1
      ,rtrim(BT.attach_fl2) ATTACH_FL2
      ,rtrim(BT.attach_fl3) ATTACH_FL3
      ,BT.sakujo_flg SAKUJO_FLG 
      ,BT.first_user FIRST_USER 
      ,BT.first_pgm FIRST_PGM 
      ,BT.first_tmsp FIRST_TMSP 
      ,BT.last_user LAST_USER 
      ,BT.last_pgm LAST_PGM 
      ,BT.last_tmsp LAST_TMSP 
      
FROM BT02NTCM BT 
     left join BR01USER BR01 on BR01.USER_ID = BT.PUB_USER
,    BM02IFCT BM02 


WHERE BT.CATE_ID = /*cateId*/'001' 
AND   BT.REG_DATE like /*regDate*/'200601%'
/*IF kanriNo != null */AND BT.KANRI_NO like /*kanriNo*/'9999%'/*END*/
AND   BT.PUB_ORG = (
		SELECT BUMON_CD
		FROM BR01USER
		WHERE USER_ID=/*userId*/'99990001'
	)
AND   BT.R_COMPANY_CD = (
		SELECT R_COMPANY_CD
		FROM BM03USCP
		WHERE USER_ID = /*userId*/'99990001'
		AND   ZOKUSEI_KBN = '1'
		
		)
AND   BT.SAKUJO_FLG <> '1'
AND   BM02.INFO_SHU='02'
AND   BT.CATE_ID =BM02.CATE_ID

order by 
       BT.PUB_DATE desc
      ,BT.kanri_no
) as sub
where row_num between /*from*/1 and /*to*/30
