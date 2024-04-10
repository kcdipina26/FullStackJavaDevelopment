<template>
  <div id="card-container">
    {{JSON.stringify(celestialObjectsList)}}
  </div>
</template>

<script>

export default {
  props: ['celestialObjectsList']
}
</script>

<style scoped>
#card-container {
  color: white;
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  justify-content: space-around;
}
</style>