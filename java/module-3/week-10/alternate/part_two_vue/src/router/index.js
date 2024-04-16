import { createRouter as _createRouter, createWebHistory } from 'vue-router'

import AllCelestialObjectsView from '../views/AllCelestialObjectsView.vue';
import CelestialObjectDetailsView from '../views/CelestialObjectDetailsView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 */

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: {name: 'allCelestialObjects'},
  },
  {
    path: '/celestial-objects',
    name: 'allCelestialObjects',
    component: AllCelestialObjectsView,
  },
  // Task 4 - update the CelestialObjectDetailsView to be dynamic
  {
    path: '/celestial-objects/:celestialObjectId',
    name: 'celestialObjectDetails',
    component: CelestialObjectDetailsView,
  },
];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
