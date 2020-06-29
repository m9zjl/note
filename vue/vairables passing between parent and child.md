# variables passing

## 1. variables passing between parent and child components

variables can be passing between parent componet and child component by the flowing ways

1. props / $emit
2. $parent / children
3. $ref

>parent component:

```typescript
<template>
    <div>
        <h1>Parent</h1>
        <H3>{{msg}}</H3>
        <m-child
                // @3.1 define ref
                ref="child"
                @showMsg="showMsg"
                :msg="'message from Parent'"/>
    </div>
</template>

<script>
    import MChild from "./Child";

    export default {
        data() {
            return {
                msg: ''
            }
        },
        methods: {
            // @1.parent parent call showMsg when child emit method showMsg
            showMsg(val) {
                this.msg = val;
            }
        },

        name: "Parent",
        components: {
            MChild
        },
        mounted() {
            // @2. parent can get all childrens by this.$children
            // and child can get all parent by this.parent
            console.log(this.$children[0].passMsg())
            // @3.2  this.$ref can get all the componets defined with ref='name'
            console.log('ref', this.$refs.child)
        }
    }
</script>

<style scoped>

</style>
```

> child component:

```typescript
<template>
    <div>
        <h3>Child</h3>
        <h3>{{ msg }}</h3>
        // @1 binding method passMsg with onclick event
        <button @click="passMsg">value in button</button>
    </div>
</template>

<script>
    export default {
        name: "Child",
        props: {
            msg: {
                type: String,
                default: '',
            }
        },
        methods: {
            passMsg() {
                // @1.child emit showMsg method to parent
                this.$emit('showMsg', 'msg from child');
            }
        },

    }
</script>

<style scoped>

</style>
```

## 2. variables passing between borthers

- data bus
- $attrs/ listener
- vuex

>data bus

```typescript
import Vue from 'vue'
export default new Vue;

// from
bus.$emit('msg', 'data from App')
// to
bus.$on('msg', (val) => {
            this.childMsg = val;
        })
```

> $attrs

```javascript
// from 
<template>
    <div id="app">
        <button @click="passMsg">passMsg</button>
        <m-parent
            :msga="a"
            :msgb="b"
            :msgc="c"
        ></m-parent>
    </div>
</template>

// to
mounted() {
        console.log(this.$children[0].passMsg())
        console.log('ref', this.$refs.child)
        console.log('attrs', this.$attrs)
    }

```
