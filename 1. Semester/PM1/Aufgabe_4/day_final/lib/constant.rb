$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
require 'ext_pr1_v4'

# sequenz for our sym definition
DAY_SYM_SEQ = [:MO, :DI, :MI, :DO, :FR, :SA, :SO]

# sequenz for our num definition
DAY_NUM_SEQ = [1, 2, 3, 4, 5, 6, 7]

# week in days for our mod operator
WEEK_IN_DAYS = DAY_SYM_SEQ.size

# Our Type Preds / Hooks
class Object
  def day?;       false end
  def day_sym?;   false end
  def day_num?;   false end
end