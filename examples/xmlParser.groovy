//def expectedResult = '''
//<shopping>
//  <category type="groceries">
//    <item>Luxury Chocolate</item>
//    <item>Luxury Coffee</item>
//  </category>
//  <category type="supplies">
//    <item>Paper</item>
//    <item quantity="6" when="Urgent">Pens</item>
//  </category>
//  <category type="present">
//    <item>Mum's Birthday</item>
//    <item when="Oct 15">Monica's Birthday</item>
//  </category>
//</shopping>
//'''


def input = '''
<shopping>
    <category type="groceries">
        <item>Chocolate</item>
        <item>Coffee</item>
    </category>
    <category type="supplies">
        <item>Paper</item>
        <item quantity="4">Pens</item>
    </category>
    <category type="present">
        <item when="Aug 10">Kathryn's Birthday</item>
    </category>
</shopping>
'''


def root = new XmlParser().parseText(input)

// modify groceries: quality items please
def groceries = root.category.findAll{ it.@type == 'groceries' }.item[0]
groceries.each { g ->
    g.value = 'Luxury ' + g.text()
}

// modify supplies: we need extra pens
def supplies = root.category.findAll{ it.@type == 'supplies' }.item[0]
supplies.findAll{ it.text() == 'Pens' }.each { s ->
    s.@quantity = s.@quantity.toInteger() + 2
    s.@when = 'Urgent'
}

// modify presents: August has come and gone
def presentCategory = root.category.find{ it.@type == 'present' }
presentCategory.children().clear()
presentCategory.appendNode('item', "Mum's Birthday")
presentCategory.appendNode('item', [when:'Oct 15'], "Monica's Birthday")

// check the when attributes
def removeNulls(list) { list.grep{it} }
assert removeNulls(root.'**'.item.@when) == ["Urgent", "Oct 15"]

// check the whole document using XmlUnit
def writer = new StringWriter()
new XmlNodePrinter(new PrintWriter(writer)).print(root)
def result = writer.toString()
