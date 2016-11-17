require_relative "converter"
require_relative "interaction"
require_relative "table"

# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
#
# A simple tool that convert lengths to metric, angel-saxon and potrzebie measures.

# start interaction with no parameters
interaction = Interaction.new(nil, nil, nil)
interaction.input

