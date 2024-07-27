# [Actúa con Prudencia](https://github.com/97-things/97-things-every-programmer-should-know/tree/master/en/thing_01)
---
[Seb Rose](https://twitter.com/sebrose)

> En todo lo que emprendas, actúa con prudencia y considere las consecuencias

*Anónimo*

No importa cuán cómodo te parezca la fecha de entrega de un proyecto, no se puede evitar sentirse presionado por el tiempo. Se te quedas teniendo que eligir entre "hacer correctamente" o "hacerlo rápido", a menudo es tentador tomar lo atajo creyendo que que volverás después para corregir lo que fuera necesario. Cuando haces esta promesa contigo mismo, tu equipo, tu cliente, lo haces en verdad. Pero casi siempre, la siguiente iteración trae nuevos problemas y te quedas absorto por ellos. Este tipo de trabajo adiado es conocido como deuda técnica y no es tu amigo. Más específicamente, [Martin Fowler](https://twitter.com/martinfowler) lo llama de deuda técnica deliberada, en su [taxonomía de la deuda técnica](https://martinfowler.com/bliki/TechnicalDebtQuadrant.html), que no debe confundirse con la deuda técnica inadvertida.

La deuda técnica es como un préstamo: te beneficias de ella a corto plazo, pero tienes que pagar intereses hasta que esté completamente saldada. Los atajos en el código dificultan la adición de funcionalidades o la refactorización del código. Son terrenos fértiles para defectos y casos de teste frágiles. Cuanto más tiempo los dejes, peor será. Cuando finalmente decidas atacar el problema original, haverá una pila de otras decisiones mediocres basadas por la solución rápida de antes, lo que hace que el código sea mucho más difícil de ser mejorado o corregido. De hecho, lo que ocurre es que la solución correcta solo es implementada cuando las cosas llegaran a un punto tan malo que no tiene más salida. Lo peor es que, en este momento, hay tanto código dependiente de lo "atajo" que es demasiado caro y arriscado cambia-lo.

Hay momentos en los que necesitarás recurrir a una deuda técnica para cumplir con un plazo o implementar una funcionalidad simple. Trata de no encontrarte en esta posición, pero si la situación lo exige absolutamente, entonces sigue adelante. Pero (y esto es un gran PERO) debes hacer un seguimiento de la deuda técnica y pagarla lo más rápido posible o las cosas se deteriorarán rápidamente Tan pronto cuando tomes esta decisión, escribe una tarjeta de tarea o regístrala en tu sistema de seguimiento de problemas para garantizar que ella no sea olvidada.

Si te programas para pagar la deuda técnica en la próxima iteración, el costo será mínimo. Dejar la deuda sin pagar acumulará intereses y este costo debe ser monitoreado y visible. Esto enfatizará su efecto en el valor comercial del proyecto y permitirá una priorización adecuada del pago. La elección de cómo calcular y registrar los intereses dependerá del proyecto en particular, pero es necesario hacer un seguimiento.

Pague la deuda técnica lo antes posible. Sería imprudente hacer lo contrario.