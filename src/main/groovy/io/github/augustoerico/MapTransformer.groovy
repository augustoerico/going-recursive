package io.github.augustoerico

class MapTransformer {

    static transform(Map map, Closure closure) {
        def newMap = [:]
        map.each { key, value ->
            switch (value) {
                case Iterable:
                    newMap."$key" = transform(value, closure)
                    break
                default:
                    newMap."$key" = closure(value)
                    break
            }
        }
        newMap
    }

    static transform(List list, Closure closure) {
        def newList = []
        list.each { value ->
            switch (value) {
                case Iterable:
                    newList << transform(value, closure)
                    break
                default:
                    newList << closure(value)
                    break
            }
        }
        newList
    }

}
