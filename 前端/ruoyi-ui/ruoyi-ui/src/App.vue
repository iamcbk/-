<template>
  <div id="app">
    <router-view />
    <theme-picker />
  </div>
</template>

<script>
import ThemePicker from "@/components/ThemePicker";

export default {
  name: "App",
  components: { ThemePicker },
  metaInfo() {
    return {
      title: '云萃商城后台管理系统'
    }
  }
};
</script>
<style scoped>
#app .theme-picker {
  display: none;
}
</style>
