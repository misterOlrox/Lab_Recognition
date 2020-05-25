abstract class MushroomDataParser {

    static List<Mushroom> parse(String path) {
        def mushrooms = new ArrayList()

        new File(path).eachLine { line ->
            String[] attributes = line.split(",")
            Mushroom newMushroom = mushroomWithAttributes(attributes.toList(), mushrooms.size() + 1)
            mushrooms.add(newMushroom)
        }

        mushrooms
    }

    static Mushroom mushroomWithAttributes(List<String> attr, int id) {
        Mushroom mushroom = new Mushroom()
        Iterator<String> currentAttr = attr.iterator()

        mushroom.id                    = id
        mushroom.edible                = currentAttr.next() == 'EDIBLE'
        mushroom.capShape              = currentAttr.next()
        mushroom.capSurface            = currentAttr.next()
        mushroom.capColor              = currentAttr.next()
        mushroom.bruises               = currentAttr.next()
        mushroom.odor                  = currentAttr.next()
        mushroom.gillAttachment        = currentAttr.next()
        mushroom.gillSpacing           = currentAttr.next()
        mushroom.gillSize              = currentAttr.next()
        mushroom.gillColor             = currentAttr.next()
        mushroom.stalkShape            = currentAttr.next()
        mushroom.stalkRoot             = currentAttr.next()
        mushroom.stalkSurfaceAboveRing = currentAttr.next()
        mushroom.stalkSurfaceBelowRing = currentAttr.next()
        mushroom.stalkColorAboveRing   = currentAttr.next()
        mushroom.stalkColorBelowRing   = currentAttr.next()
        mushroom.veilType              = currentAttr.next()
        mushroom.veilColor             = currentAttr.next()
        mushroom.ringNumber            = currentAttr.next()
        mushroom.ringType              = currentAttr.next()
        mushroom.sporePrintColor       = currentAttr.next()
        mushroom.population            = currentAttr.next()
        mushroom.habitat               = currentAttr.next()

        mushroom
    }
}
