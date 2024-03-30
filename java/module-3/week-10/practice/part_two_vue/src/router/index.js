import { createRouter as _createRouter, createWebHistory } from 'vue-router'

import AuthorDetailsView from '../views/AuthorDetailsView.vue';
import BookDetailsView from '../views/BookDetailsView.vue';
import AllBooksView from '../views/AllBooksView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 */

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: {name: 'allBooks'},
  },
  {
    path: '/authors/:authorId',
    name: 'authorDetails',
    component: AuthorDetailsView,
  },
  // Task 3 - update the BookDetailsView to be dynamic
  {
    path: '/books/:bookId',
    name: 'bookDetails',
    component: BookDetailsView,
  },
  {
    path: '/books',
    name: 'allBooks',
    component: AllBooksView,
  }
];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
