
# Purpose:    Eine Funktion schreiben, die es ermöglicht, eine Uhr vorrauszustellen.
# Contrackt:  set_clock::=(now_time, future_time)
#             :: Array[Nat,Nat,Nat] x Array[Nat,Nat,Nat] ->? Array[Nat,Nat,Nat] ::
#             ((now_time[0] < LIMIT_D_HOUR) and (now_time[1] < LIMIT_MIN) and (now_time[2] < LIMIT_SEC))::
# Test: {
#           [10,20,0],[2,30,0] => [12,50,0], [20,59,59],[1,1,1] => [22,0,0], 
#           [5,10],[3,5,10] => Err, [10,5,0],[5,10] => Err, [-5,30,10],[3,5,10] => Err 
#       }

$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
require 'ext_pr1_v4'

#------------GLOBAL VAR:----------------------
LIMIT_D_HOUR = 24
LIMIT_MIN = 60
LIMIT_SEC = 60
#------------CONVERT SECONDS:----------------------
def time_in_sec(time)
(((time[0] * LIMIT_MIN ) + time[1] ) * LIMIT_SEC) + time[2]
end

def set_clock(now_time, future_time)
  #------------PRE COND.:----------------------
  check_pre((now_time.array? and future_time.array?))
  check_pre(((now_time.size == 3) and (future_time.size == 3)))
  check_pre((now_time[0-3].nat? and future_time[0-3].nat?))
  check_pre(((now_time[0] < LIMIT_D_HOUR) and (now_time[1] < LIMIT_MIN) and (now_time[2] < LIMIT_SEC)))
  #------------KONVERSION:---------------------
  sum_time_sec = time_in_sec(now_time) + time_in_sec(future_time)
  #-------------SAVE:--------------------------
  h = now_time[0] + future_time[0]
  m = now_time[1] + future_time[1]
  #-------------REKONVERSION:------------------
  hour = sum_time_sec / 3600
  min = (sum_time_sec - (h * 3600)) / 60 %LIMIT_MIN
  sec = (sum_time_sec - (h * 3600) - (m * 60)) %LIMIT_SEC
  #-------------RESULTAT:----------------------
  Array[hour, min, sec]
end
