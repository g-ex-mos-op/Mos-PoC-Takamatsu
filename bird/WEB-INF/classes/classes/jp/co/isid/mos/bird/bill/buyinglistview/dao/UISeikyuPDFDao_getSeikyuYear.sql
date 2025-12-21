select
    distinct substr(BS02.SEIKYUSHO_ID, 1, 4) as SEIKYU_DT 
from
    BS02SEKR as BS02 
order by
    SEIKYU_DT desc