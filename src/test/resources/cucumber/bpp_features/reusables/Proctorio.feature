Feature: Proctorio
  Submit

  @Proctorio
  Scenario Outline: Submit <username>
    When I am on "https://my.bpp.com/vle/" URL
    When I click on the "Proctorio Login Link" element
    And I click on the "Proctorio Special Accounts" element
    And I fill the "Proctorio Username Field" field with "<username>"
    And I fill the "Proctorio Password Field" field with "<password>"
    And I click on the "Proctorio Login Button" element
    And I click on the "Proctorio Test" element
    And I click on the "Proctorio Yes" element
    And I click on the "Proctorio Account" element
    And I click on the "Proctorio Logout" element

    Examples:
    |username              |password    |
#    |proctorio-loadtest1990|Password1!!!|
#    |proctorio-loadtest1991|Password1!!!|

    |proctorio-loadtest101|Password1!!!|
    |proctorio-loadtest102|Password1!!!|
    |proctorio-loadtest103|Password1!!!|
    |proctorio-loadtest104|Password1!!!|
    |proctorio-loadtest105|Password1!!!|
    |proctorio-loadtest106|Password1!!!|
    |proctorio-loadtest107|Password1!!!|
    |proctorio-loadtest108|Password1!!!|
    |proctorio-loadtest109|Password1!!!|
    |proctorio-loadtest110|Password1!!!|
    |proctorio-loadtest111|Password1!!!|
    |proctorio-loadtest112|Password1!!!|
    |proctorio-loadtest113|Password1!!!|
    |proctorio-loadtest114|Password1!!!|
    |proctorio-loadtest115|Password1!!!|
    |proctorio-loadtest116|Password1!!!|
    |proctorio-loadtest117|Password1!!!|
    |proctorio-loadtest118|Password1!!!|
    |proctorio-loadtest119|Password1!!!|
    |proctorio-loadtest120|Password1!!!|
    |proctorio-loadtest121|Password1!!!|
    |proctorio-loadtest122|Password1!!!|
    |proctorio-loadtest123|Password1!!!|
    |proctorio-loadtest124|Password1!!!|
    |proctorio-loadtest125|Password1!!!|
    |proctorio-loadtest126|Password1!!!|
    |proctorio-loadtest127|Password1!!!|
    |proctorio-loadtest128|Password1!!!|
    |proctorio-loadtest129|Password1!!!|
    |proctorio-loadtest130|Password1!!!|
    |proctorio-loadtest131|Password1!!!|
    |proctorio-loadtest132|Password1!!!|
    |proctorio-loadtest133|Password1!!!|
    |proctorio-loadtest134|Password1!!!|
    |proctorio-loadtest135|Password1!!!|
    |proctorio-loadtest136|Password1!!!|
    |proctorio-loadtest137|Password1!!!|
    |proctorio-loadtest138|Password1!!!|
    |proctorio-loadtest139|Password1!!!|
    |proctorio-loadtest140|Password1!!!|
    |proctorio-loadtest141|Password1!!!|
    |proctorio-loadtest142|Password1!!!|
    |proctorio-loadtest143|Password1!!!|
    |proctorio-loadtest144|Password1!!!|
    |proctorio-loadtest145|Password1!!!|
    |proctorio-loadtest146|Password1!!!|
    |proctorio-loadtest147|Password1!!!|
    |proctorio-loadtest148|Password1!!!|
    |proctorio-loadtest149|Password1!!!|
    |proctorio-loadtest150|Password1!!!|
    |proctorio-loadtest151|Password1!!!|
    |proctorio-loadtest152|Password1!!!|
    |proctorio-loadtest153|Password1!!!|
    |proctorio-loadtest154|Password1!!!|
    |proctorio-loadtest155|Password1!!!|
    |proctorio-loadtest156|Password1!!!|
    |proctorio-loadtest157|Password1!!!|
    |proctorio-loadtest158|Password1!!!|
    |proctorio-loadtest159|Password1!!!|
    |proctorio-loadtest160|Password1!!!|
    |proctorio-loadtest161|Password1!!!|
    |proctorio-loadtest162|Password1!!!|
    |proctorio-loadtest163|Password1!!!|
    |proctorio-loadtest164|Password1!!!|
    |proctorio-loadtest165|Password1!!!|
    |proctorio-loadtest166|Password1!!!|
    |proctorio-loadtest167|Password1!!!|
    |proctorio-loadtest168|Password1!!!|
    |proctorio-loadtest169|Password1!!!|
    |proctorio-loadtest170|Password1!!!|
    |proctorio-loadtest171|Password1!!!|
    |proctorio-loadtest172|Password1!!!|
    |proctorio-loadtest173|Password1!!!|
    |proctorio-loadtest174|Password1!!!|
    |proctorio-loadtest175|Password1!!!|
    |proctorio-loadtest176|Password1!!!|
    |proctorio-loadtest177|Password1!!!|
    |proctorio-loadtest178|Password1!!!|
    |proctorio-loadtest179|Password1!!!|
    |proctorio-loadtest180|Password1!!!|
    |proctorio-loadtest181|Password1!!!|
    |proctorio-loadtest182|Password1!!!|
    |proctorio-loadtest183|Password1!!!|
    |proctorio-loadtest184|Password1!!!|
    |proctorio-loadtest185|Password1!!!|
    |proctorio-loadtest186|Password1!!!|
    |proctorio-loadtest187|Password1!!!|
    |proctorio-loadtest188|Password1!!!|
    |proctorio-loadtest189|Password1!!!|
    |proctorio-loadtest190|Password1!!!|
    |proctorio-loadtest191|Password1!!!|
    |proctorio-loadtest192|Password1!!!|
    |proctorio-loadtest193|Password1!!!|
    |proctorio-loadtest194|Password1!!!|
    |proctorio-loadtest195|Password1!!!|
    |proctorio-loadtest196|Password1!!!|
    |proctorio-loadtest197|Password1!!!|
    |proctorio-loadtest198|Password1!!!|
    |proctorio-loadtest199|Password1!!!|
    |proctorio-loadtest200|Password1!!!|
    |proctorio-loadtest201|Password1!!!|
    |proctorio-loadtest202|Password1!!!|
    |proctorio-loadtest203|Password1!!!|
    |proctorio-loadtest204|Password1!!!|
    |proctorio-loadtest205|Password1!!!|
    |proctorio-loadtest206|Password1!!!|
    |proctorio-loadtest207|Password1!!!|
    |proctorio-loadtest208|Password1!!!|
    |proctorio-loadtest209|Password1!!!|
    |proctorio-loadtest210|Password1!!!|
    |proctorio-loadtest211|Password1!!!|
    |proctorio-loadtest212|Password1!!!|
    |proctorio-loadtest213|Password1!!!|
    |proctorio-loadtest214|Password1!!!|
    |proctorio-loadtest215|Password1!!!|
    |proctorio-loadtest216|Password1!!!|
    |proctorio-loadtest217|Password1!!!|
    |proctorio-loadtest218|Password1!!!|
    |proctorio-loadtest219|Password1!!!|
    |proctorio-loadtest220|Password1!!!|
    |proctorio-loadtest221|Password1!!!|
    |proctorio-loadtest222|Password1!!!|
    |proctorio-loadtest223|Password1!!!|
    |proctorio-loadtest224|Password1!!!|
    |proctorio-loadtest225|Password1!!!|
    |proctorio-loadtest226|Password1!!!|
    |proctorio-loadtest227|Password1!!!|
    |proctorio-loadtest228|Password1!!!|
    |proctorio-loadtest229|Password1!!!|
    |proctorio-loadtest230|Password1!!!|
    |proctorio-loadtest231|Password1!!!|
    |proctorio-loadtest232|Password1!!!|
    |proctorio-loadtest233|Password1!!!|
    |proctorio-loadtest234|Password1!!!|
    |proctorio-loadtest235|Password1!!!|
    |proctorio-loadtest236|Password1!!!|
    |proctorio-loadtest237|Password1!!!|
    |proctorio-loadtest238|Password1!!!|
    |proctorio-loadtest239|Password1!!!|
    |proctorio-loadtest240|Password1!!!|
    |proctorio-loadtest241|Password1!!!|
    |proctorio-loadtest242|Password1!!!|
    |proctorio-loadtest243|Password1!!!|
    |proctorio-loadtest244|Password1!!!|
    |proctorio-loadtest245|Password1!!!|
    |proctorio-loadtest246|Password1!!!|
    |proctorio-loadtest247|Password1!!!|
    |proctorio-loadtest248|Password1!!!|
    |proctorio-loadtest249|Password1!!!|
    |proctorio-loadtest250|Password1!!!|
    |proctorio-loadtest251|Password1!!!|
    |proctorio-loadtest252|Password1!!!|
    |proctorio-loadtest253|Password1!!!|
    |proctorio-loadtest254|Password1!!!|
    |proctorio-loadtest255|Password1!!!|
    |proctorio-loadtest256|Password1!!!|
    |proctorio-loadtest257|Password1!!!|
    |proctorio-loadtest258|Password1!!!|
    |proctorio-loadtest259|Password1!!!|
    |proctorio-loadtest260|Password1!!!|
    |proctorio-loadtest261|Password1!!!|
    |proctorio-loadtest262|Password1!!!|
    |proctorio-loadtest263|Password1!!!|
    |proctorio-loadtest264|Password1!!!|
    |proctorio-loadtest265|Password1!!!|
    |proctorio-loadtest266|Password1!!!|
    |proctorio-loadtest267|Password1!!!|
    |proctorio-loadtest268|Password1!!!|
    |proctorio-loadtest269|Password1!!!|
    |proctorio-loadtest270|Password1!!!|
    |proctorio-loadtest271|Password1!!!|
    |proctorio-loadtest272|Password1!!!|
    |proctorio-loadtest273|Password1!!!|
    |proctorio-loadtest274|Password1!!!|
    |proctorio-loadtest275|Password1!!!|
    |proctorio-loadtest276|Password1!!!|
    |proctorio-loadtest277|Password1!!!|
    |proctorio-loadtest278|Password1!!!|
    |proctorio-loadtest279|Password1!!!|
    |proctorio-loadtest280|Password1!!!|




