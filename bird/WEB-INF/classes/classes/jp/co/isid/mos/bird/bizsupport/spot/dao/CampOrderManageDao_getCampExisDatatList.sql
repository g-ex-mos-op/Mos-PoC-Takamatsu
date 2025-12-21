select * 
from 
     BM53CPJK 
where 
      POS_FROM_DT <= /*sysDateMinOne*/ 
      and  
      POS_END_DT >= /*sysDateMinThr*/
order by 
      CMP_NO      
      