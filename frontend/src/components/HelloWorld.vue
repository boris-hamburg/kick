<template>
  <div>
    <p v-if="isConnected">Connected to Server</p>
    <input v-model="firstName" class="input" type="text" placeholder="Vorname">
    <input v-model="lastName" class="input" type="text" placeholder="Nachname">
    <button @click="sendParticipant()">Dabei</button>
    <button @click="sendNonParticipant()">Raus</button>
    <div v-if="spieltag">
      <h4>Participants:</h4>
      <ol>
          <li v-bind:key="teilnehmer.id" v-for="teilnehmer in spieltag.teilnehmer">
            {{ teilnehmer.firstName}} {{ teilnehmer.lastName }}
          </li>
      </ol>
    </div>
    <div v-if="nonparticipants">
      <h4>Non-Participants:</h4>
      <ol>
        <li v-bind:key="nonparticipant.id" v-for="nonparticipant in nonparticipants">
          {{ nonparticipant.firstName }} {{ nonparticipant.lastName }}
        </li>
      </ol>
    </div>
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
      nonparticipants: null,
      spieltag: null,
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
          this.spieltag = JSON.parse(frame.body)
        })
        this.ws.subscribe('/topic/nonparticipants', (frame) => {
          this.nonparticipants = JSON.parse(frame.body)
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
        this.ws.send('/app/participant', JSON.stringify({
          'firstName': this.firstName,
          'lastName': this.lastName}), {})
      }
    },
    sendNonParticipant () {
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
