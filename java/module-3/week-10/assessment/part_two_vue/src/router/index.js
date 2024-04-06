import { createRouter as _createRouter, createWebHistory } from 'vue-router'

import MovieDetailsView from '../views/MovieDetailsView.vue';
import AllMoviesView from '../views/AllMoviesView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 */

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: {name: 'allMovies'},
  },
  // Task 4 - update the MovieDetailsView to be dynamic
  {
    path: '/movies/:movieId',
    name: 'movieDetails',
    component: MovieDetailsView,
  },
  {
    path: '/movies',
    name: 'allMovies',
    component: AllMoviesView,
  }
];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
