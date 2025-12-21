select sub.* 
from 
(
SELECT
   row_number() over(order by BT.reg_date, BT.seq) as ROW_NUM 
  ,BM02.SORT_KEY
  ,BM17.SORT_KEY
  ,'04' as INFO_SHU 
  ,BT.REG_DATE 
  ,BT.SEQ 
  ,BT.CATE_ID 
  ,BM02.CATE_NAME 
  ,BT.SUB_CATE_ID 
  ,BM17.SUB_CATE_NAME 
  ,rtrim(BT.TITLE) as TITLE
  ,rtrim(BT.FILE_NAME) as FILE_NAME
FROM 
  ((BT04FRMM AS BT 
      INNER JOIN BM02IFCT AS BM02 ON BT.CATE_ID=BM02.CATE_ID ) 
      INNER JOIN BM17SBCT AS BM17 ON BT.CATE_ID=BM17.CATE_ID 
                                 AND BT.SUB_CATE_ID=BM17.SUB_CATE_ID
                                 AND BM17.INFO_SHU=BM02.INFO_SHU) 

WHERE BT.SAKUJO_FLG <> '1' 
AND   BT.PUB_DATE_TO >= /*sysDate*/'20070810'
AND BM02.INFO_SHU = /*INFO_SHU*/'04'
/*IF CATE_ID != null */AND BT.CATE_ID = /*CATE_ID*/'01'/*END*/
/*IF SUB_CATE_ID != null */AND BT.SUB_CATE_ID = /*SUB_CATE_ID*/'01'/*END*/
/*IF TITLE != null */AND BT.TITLE like /*TITLE*/'%99999999%'/*END*/
/*IF regDate != null */AND BT.REG_DATE like /*regDate*/'999912%'/*END*/
ORDER BY BM02.SORT_KEY
,        BM17.SORT_KEY
,        BT.REG_DATE DESC
,        BT.SEQ DESC
) as sub

