<template>
  <div>
    <p v-if="isConnected">Connected to Server</p>
    <p v-if="participant">Participant: {{ participant }}</p>
    <p v-if="nonparticipant">Non-Participant: {{ nonparticipant }}</p>
    <input v-model="firstName" class="input" type="text" placeholder="Vorname">
    <input v-model="lastName" class="input" type="text" placeholder="Nachname">
    <button @click="sendParticipant()">Dabei</button>
    <button @click="sendNonParticiapant()">Raus</button>
  </div>
</template>

<script>
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'

export default {
  name: 'HelloWorld',
  data () {
    return {
      isConnected: false,
      participant: null,
      nonparticipant: null,
      firstName: '',
      lastName: ''
    }
  },
  methods: {
    connect () {
      var socket = new SockJS('http://localhost:8088/websocket-endpoint')
      this.ws = Stomp.over(socket)

      this.ws.connect({}, (frame) => {
        this.isConnected = true
        this.ws.subscribe('/topic/participants', (frame) => {
          this.participant = JSON.parse(frame.body)
        })
        this.ws.subscribe('/topic/nonparticipants', (frame) => {
          this.nonparticipant = JSON.parse(frame.body)
        })
      }, (error) => {
        this.isConnected = false
        console.log(error)
      })
    },
    disconnect () {
      if (this.ws != null) {
        this.ws.disconnect()
      }
      this.isConnected = false
    },
    sendParticipant () {
      if (this.ws != null) {
        console.log('FirstName sent: ' + this.firstName)
        this.ws.send('/app/participant', JSON.stringify({
          'firstName': this.firstName,
          'lastName': this.lastName}), {})
      }
    },
    sendNonParticiapant () {
      if (this.ws != null) {
        this.ws.send('/app/nonparticipant', JSON.stringify({
          'firstName': this.firstName,
          'lastName': this.lastName}), {})
      }
    }
  },
  mounted () {
    this.connect()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
