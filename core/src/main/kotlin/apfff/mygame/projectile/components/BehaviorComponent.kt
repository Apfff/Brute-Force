package apfff.mygame.projectile.components

import apfff.mygame.projectile.Projectile

//how the projectile decides or generates forces
//e.g.: homing, orbiting, boomerang return, wobble, delayed explosion

interface BehaviorComponent {
  fun step(projectile: Projectile, dt: Float)
}