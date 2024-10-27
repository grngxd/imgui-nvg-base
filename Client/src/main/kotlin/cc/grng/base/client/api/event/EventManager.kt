package cc.grng.edge.event

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import kotlin.reflect.KClass

/**
 * @author refactoring, grng
 * @date 01-09-2023
 * @description Created by refactoring, modified by grng
 */
class EventManager {
    companion object {
        val instance = EventManager()
    }

    private val subscribers = mutableMapOf<KClass<*>, Any>()
    private val cache = mutableMapOf<KClass<*>, List<Method>>()

    fun register(subscriber: Any) {
        val clazz = subscriber::class
        if (subscribers.containsKey(clazz)) return
        subscribers[clazz] = subscriber
        cache[clazz] = clazz.java.declaredMethods.filter { method ->
            method.isAnnotationPresent(Subscribe::class.java)
        }
    }

    fun unregister(subscriber: Any? = null) {
        subscriber?.let {
            val clazz = it::class
            subscribers.remove(clazz)
            cache.remove(clazz)
        } ?: run {
            subscribers.clear()
            cache.clear()
        }
    }

    fun fire(event: Event) {
        subscribers.forEach { (clazz, obj) ->
            cache[clazz]?.filter { method ->
                method.getAnnotation(Subscribe::class.java).target == event::class
            }?.sortedByDescending { method ->
                method.getAnnotation(Subscribe::class.java).priority.ordinal
            }?.forEach { method ->
                try {
                    method.invoke(obj, event)
                } catch (e: IllegalAccessException) {
                    throw RuntimeException(e)
                } catch (e: InvocationTargetException) {
                    throw RuntimeException(e)
                }
            }
        }
    }
}