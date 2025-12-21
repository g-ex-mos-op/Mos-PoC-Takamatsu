update BT26UPJK

set RENEW4_ENT_YEAR  = /*entity.entryYear*/'' 
,   RENEW4_ENT_KAI  = /*entity.entryKai*/'' 
,   RENEW4_COURSE  = /*entity.compleCourseCd*/''
,   RENEW4_COURSE_NAME  = /*entity.compleCourseName*/''
,   RENEW4_DATE  = /*entity.compleCourseDt*/''
,   RENEW4_STATUS  = /*entity.courseStatus*/''
,   LAST_USER  = /*entity.lastUser*/''
,   LAST_PGM  = /*entity.lastPgm*/'BSM006'
,   LAST_TMSP  = /*entity.lastTmsp*/

where STAFF_ID = /*entity.staffId*/''
and   LICENSE_DT = /*entity.licenseDt*/''
and   RENEW4_STATUS <> '1'