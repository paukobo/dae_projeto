<template>
  <b-container>
    <div>
      <h1>Create a new Prescrição</h1>
      <form @submit.prevent="create" :disabled="!isFormValid">
        <b-form-group description="The descrição is required" label="Enter descrição" label-for="descricao"
                      :invalid-feedback="invalidDescricaoFeedback" :state="isDescricaoValid">
          <b-input v-model.trim="descricao" required :state="isDescricaoValid" placeholder="Enter descrição"/>
        </b-form-group>

        <b-form-group description="The data inicial is required" label="Enter data inicial" label-for="dataInicio"
                      :invalid-feedback="invalidDatesFeedback" :state="isDateValid">
          <b-input ref="dataInicio" v-model.trim="dataInicio" type="date" :state="isDateValid" required placeholder="Enter data inicial"/>
        </b-form-group>

        <b-form-group description="The data final is required" label="Enter data final" label-for="dataFim"
                      :invalid-feedback="invalidDatesFeedback" :state="isDateValid">
          <b-input ref="dataFim" v-model.trim="dataFim" type="date" :state="isDateValid" required placeholder="Enter data final"/>
        </b-form-group>

        <b-form-group description="The duração is required" label="Enter duração" label-for="duracao"
                      :invalid-feedback="invalidDuracaoFeedback" :state="isDuracaoValid">
          <b-input v-model="duracao" type="number" :state="isDuracaoValid" required
                   placeholder="Enter duração"/>
        </b-form-group>

        <p class="text-danger" v-show="errorMsg">{{ errorMsg }}</p>
        <nuxt-link to="/prescricoes">Return</nuxt-link>
        <button type="reset" @click="reset">RESET</button>
        <button @click.prevent="create" :disabled="!isFormValid">CREATE</button>
      </form>
    </div>
  </b-container>
</template>
<script>
import moment from "moment";

export default {
  data() {
    return {
      descricao: null,
      dataInicio: null,
      dataFim: null,
      duracao: null,
      prescricoes: [],
      errorMsg: false
    }
  },
  computed: {
    invalidDuracaoFeedback() {
      if (!this.duracao) {
        return null
      }
      if (this.duracao <= 0) {
        return 'The duração must be greater than 0.'
      }
      return ''
    },
    isDuracaoValid() {
      if (!this.duracao) {
        return null
      }
      if (this.duracao <= 0) {
        return false
      }
      return true
    },
    invalidDescricaoFeedback() {
      if (!this.descricao) {
        return null
      }
      let descricaoLen = this.descricao.length
      if (descricaoLen < 2 || descricaoLen > 150) {
        return 'The descrição must have between [3, 150] characters.'
      }
      return ''
    },
    isDescricaoValid() {
      if (!this.descricao) {
        return null
      }
      let descricaoLen = this.descricao.length
      if (descricaoLen < 2 || descricaoLen > 150) {
        return false
      }
      return true
    },
    invalidDatesFeedback() {
      if (!this.dataInicio) {
        return null
      }
      if (!this.dataFim) {
        return null
      }
      return ''
    },
    isDateValid() {
      if (!this.dataInicio) {
        return null
      }
      if (!this.dataFim) {
        return null
      }
      return true
    },
    // invalidDateInicialFeedback() {
    //   if (!this.dataInicio) {
    //     return null
    //   }
    //   return ''
    // },
    // isDateInicialValid() {
    //   if (!this.dataInicio) {
    //     return null
    //   }
    //   return true
    // },
    // invalidDateFinalFeedback() {
    //   if (!this.dataFim) {
    //     return null
    //   }
    //   return ''
    // },
    // isDateFinalValid() {
    //   if (!this.dataFim) {
    //     return null
    //   }
    //   return true
    // },
    isFormValid() {
      if (!this.isDescricaoValid) {
        return false
      }
      if (!this.isDateValid) {
        return false
      }
      if (!this.isDuracaoValid) {
        return false
      }
      return true
    }
  },
  created() {
    this.$axios.$get('/api/prescricoes')
      .then(prescricoes => {
        this.prescricoes = prescricoes
      })
  },
  methods: {
    reset() {
      this.errorMsg = false
    },
    create() {
      if (this.calcDate()==="1") {
        this.$toast.error("[ERRO] A data de início não pode ser anterior à data de hoje!").goAway(3000)
      }
      if (this.calcDate()==="2"){
        this.$toast.error("[ERRO] A data de fim não pode ser anterior à data de início!").goAway(3000)
      }
      if (this.calcDate()==="0"){
      this.$axios.$post('/api/prescricoes', {
        descricao: this.descricao,
        dataInicio: this.dataInicio,
        dataFim: this.dataFim,
        duracao: this.duracao
      })
        .then(() => {
          this.$toast.success("Prescrição created successfully!").goAway(2000)
          this.$router.push('/prescricoes')
        })
    }
    },
    calcDate() {
      let nowDate = moment();
      let startDate = moment(this.dataInicio, 'YYYY-MM-DD');
      let endDate = moment(this.dataFim, 'YYYY-MM-DD');
      let dateDiffStart = moment.duration(nowDate.diff(startDate));
      let dateDiffEnd = moment.duration(startDate.diff(endDate));

      if(dateDiffStart.years()>0
      || dateDiffStart.years()===0 && dateDiffStart.months()>0
      || dateDiffStart.years()===0 && dateDiffStart.months()===0 && dateDiffStart.days()>0){
        return "1";
      }
      if(dateDiffEnd.years()>0
        || dateDiffEnd.years()===0 && dateDiffEnd.months()>0
        || dateDiffEnd.years()===0 && dateDiffEnd.months()===0 && dateDiffEnd.days()>0) {
        return "2";
      }
      return "0";
    }
  }
}
</script>
