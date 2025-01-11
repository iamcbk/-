<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入商品名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="积分套餐类别" prop="pointsCategoryId">
        <el-input
          v-model="queryParams.pointsCategoryId"
          placeholder="请输入积分套餐类别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['piont_goods:piont_goods:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['piont_goods:piont_goods:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['piont_goods:piont_goods:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['piont_goods:piont_goods:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="piont_goodsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="积分商品ID" align="center" prop="pointsGoodId" />
      <el-table-column label="图片集" align="center" prop="photo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="商品名" align="center" prop="title" />
      <el-table-column label="积分套餐类别" align="center" prop="pointsCategoryId" />
      <el-table-column label="商品描述" align="center" prop="pointsDescription" />
      <el-table-column label="已售件数" align="center" prop="soldNumber" />
      <el-table-column label="积分值" align="center" prop="points" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['piont_goods:piont_goods:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['piont_goods:piont_goods:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改积分商品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图片集" prop="photo">
          <image-upload v-model="form.photo"/>
        </el-form-item>
        <el-form-item label="商品名" prop="title">
          <el-input v-model="form.title" placeholder="请输入商品名" />
        </el-form-item>
        <el-form-item label="积分套餐类别" prop="pointsCategoryId">
          <el-input v-model="form.pointsCategoryId" placeholder="请输入积分套餐类别" />
        </el-form-item>
        <el-form-item label="商品描述" prop="pointsDescription">
          <el-input v-model="form.pointsDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="已售件数" prop="soldNumber">
          <el-input v-model="form.soldNumber" placeholder="请输入已售件数" />
        </el-form-item>
        <el-form-item label="积分值" prop="points">
          <el-input v-model="form.points" placeholder="请输入积分值" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPiont_goods, getPiont_goods, delPiont_goods, addPiont_goods, updatePiont_goods } from "@/api/piont_goods/piont_goods";

export default {
  name: "Piont_goods",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 积分商品表格数据
      piont_goodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        pointsCategoryId: null,
        pointsDescription: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询积分商品列表 */
    getList() {
      this.loading = true;
      listPiont_goods(this.queryParams).then(response => {
        this.piont_goodsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        pointsGoodId: null,
        photo: null,
        title: null,
        pointsCategoryId: null,
        pointsDescription: null,
        soldNumber: null,
        points: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.pointsGoodId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加积分商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pointsGoodId = row.pointsGoodId || this.ids
      getPiont_goods(pointsGoodId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改积分商品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pointsGoodId != null) {
            updatePiont_goods(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPiont_goods(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const pointsGoodIds = row.pointsGoodId || this.ids;
      this.$modal.confirm('是否确认删除积分商品编号为"' + pointsGoodIds + '"的数据项？').then(function() {
        return delPiont_goods(pointsGoodIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('piont_goods/piont_goods/export', {
        ...this.queryParams
      }, `piont_goods_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
