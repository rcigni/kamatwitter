
import groovy.xml.MarkupBuilder

class Car {
    String name, make
    int year
    String country
}

def cars = [
        new Car(name:'HSV Maloo', make:'Holden', year:2006, country: 'Australia'),
        new Car(name:'HSV Maloo II', make:'Holden', year:2010, country: 'Australia')
]

def writer = new StringWriter()
def xml = new MarkupBuilder(writer)
xml.records() {
    cars.each {_car ->
        car(name: _car.name, make: _car.make, year: _car.year) {
            country(_car.country)
        }
    }
}

println writer

