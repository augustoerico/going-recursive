package io.github.augustoerico

class DataTransformer {

    static transform(Map map, Closure closure) {
        def newMap = [:]
        map.each { key, value ->
            if (value instanceof Map || value instanceof List) {
                newMap."$key" = transform(value, closure)
            } else {
                newMap."$key" = closure(value)
            }
        }
        newMap
    }

    static transform(List list, Closure closure) {
        def newList = []
        list.each { value ->
            if (value instanceof Map || value instanceof List) {
                newList << transform(value, closure)
            } else {
                newList << closure(value)
            }
        }
        newList
    }

}
