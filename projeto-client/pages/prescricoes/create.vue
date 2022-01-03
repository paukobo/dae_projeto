<template>
  <b-container>
    <div>
      <h1>Criar nova Prescrição</h1>
      <form @submit.prevent="create" :disabled="!isFormValid">
        <b-form-group description="A descrição é obrigatória" label="Inserir descrição" label-for="descricao"
                      :invalid-feedback="invalidDescricaoFeedback" :state="isDescricaoValid">
          <b-input v-model.trim="descricao" required :state="isDescricaoValid" placeholder="Inserir descrição"/>
        </b-form-group>

        <b-form-group description="A data inicial é obrigatória" label="Inserir data inicial" label-for="dataInicio"
                      :invalid-feedback="invalidDatesFeedback" :state="isDateValid">
          <b-input ref="dataInicio" v-model.trim="dataInicio" type="date" :state="isDateValid" required placeholder="Inserir data inicial"/>
        </b-form-group>

        <b-form-group description="A data final é obrigatória" label="Inserir data final" label-for="dataFim"
                      :invalid-feedback="invalidDatesFeedback" :state="isDateValid">
          <b-input ref="dataFim" v-model.trim="dataFim" type="date" :state="isDateValid" required placeholder="Inserir data final"/>
        </b-form-group>

        <b-form-group description="A duração é obrigatória" label="Inserir duração" label-for="duracao"
                      :invalid-feedback="invalidDuracaoFeedback" :state="isDuracaoValid">
          <b-input v-model="duracao" type="number" :state="isDuracaoValid" required
                   placeholder="Inserir duração"/>
        </b-form-group>

        <p class="text-danger" v-show="errorMsg">{{ errorMsg }}</p>
        <b-button @click="$router.go(-1)">Voltar</b-button>
        <b-button variant="danger" type="reset" @click="reset">Reset</b-button>
        <b-button variant="success" @click.prevent="create" :disabled="!isFormValid">Criar</b-button>
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
          this.$toast.success("Prescrição criada com sucesso!").goAway(2000)
          this.$router.go(-1)
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
