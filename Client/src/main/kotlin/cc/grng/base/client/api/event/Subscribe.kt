package cc.grng.edge.event

import kotlin.reflect.KClass

/**
 * @author refactoring
 * @date 01-09-2023
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(
    AnnotationRetention.RUNTIME
)
annotation class Subscribe(val target: KClass<*>, val priority: EventPriority = EventPriority.MEDIUM)