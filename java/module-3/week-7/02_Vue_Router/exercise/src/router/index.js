import { createRouter as _createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import MyBooksView from '@/views/MyBooksView.vue';
import NewBookView from '@/views/NewBookView.vue';
import BookDetails from '@/components/BookDetails.vue';

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/myBooks",
    name: "myBooks",
    component: MyBooksView,
  },
  {
    path: "/addBook",
    name: "newBook",
    component: NewBookView,
  },
  {
    path: "/book/:isbn",
    name: "book",
    component: BookDetails,
    props: true,
  },


];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes
  });
}

