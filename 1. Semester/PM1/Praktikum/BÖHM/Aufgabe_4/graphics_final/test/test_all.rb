# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')
$:.unshift File.join(File.dirname(__FILE__),'..','test')
require 'test/unit'
require 'point2d_test'
require 'point_test'
require 'range2d_test'
require 'range_test'
require 'union_test'
require 'union2d_test'
require 'main'

