package apfff.mygame.entities.projectile.components

import com.badlogic.gdx.math.Vector2


//how the projectile responds to forces
//e.g.: drag, gravity, bounce, rotation

interface PhysicsComponent {
  fun step(velocity: Vector2, dt: Float)
}




fun orderPhysicsComponents(physicsComponents: ArrayList<PhysicsComponent>) {
  physicsComponents.sortedBy {
    physicsComponentsStepOrder[it::class]
  }
}

private val physicsComponentsStepOrder = mapOf(
  RotationComponent::class to 0,
  DragComponent::class to 1,
)