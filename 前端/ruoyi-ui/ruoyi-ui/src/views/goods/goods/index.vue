<template>
  <div class="app-container">
    <el-row :gutter="20" class="statistics-row">
      <el-col :span="8">
        <div class="statistics-card" :class="{ 'is-hover': isHover1 }" @mouseenter="isHover1 = true" @mouseleave="isHover1 = false">
          <div class="card-body">
            <div class="icon-wrapper bg-primary">
              <i class="el-icon-goods"></i>
              <div class="icon-shadow"></div>
              <div class="icon-glow"></div>
            </div>
            <div class="content">
              <div class="title">商品总数</div>
              <div class="value-wrapper">
                <span class="value">{{ total }}</span>
                <span class="unit">件</span>
              </div>
            </div>
          </div>
          <div class="decoration-line"></div>
          <div class="progress-bar">
            <div class="progress" :style="{ width: '75%' }"></div>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="statistics-card" :class="{ 'is-hover': isHover2 }" @mouseenter="isHover2 = true" @mouseleave="isHover2 = false">
          <div class="card-body">
            <div class="icon-wrapper bg-success">
              <i class="el-icon-shopping-cart-full"></i>
              <div class="icon-shadow"></div>
              <div class="icon-glow"></div>
            </div>
            <div class="content">
              <div class="title">在售商品</div>
              <div class="value-wrapper">
                <span class="value">{{ onSaleCount }}</span>
                <span class="unit">件</span>
              </div>
            </div>
          </div>
          <div class="decoration-line"></div>
          <div class="progress-bar">
            <div class="progress success" :style="{ width: '98%' }"></div>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="statistics-card" :class="{ 'is-hover': isHover3 }" @mouseenter="isHover3 = true" @mouseleave="isHover3 = false">
          <div class="card-body">
            <div class="icon-wrapper bg-warning">
              <i class="el-icon-collection"></i>
              <div class="icon-shadow"></div>
              <div class="icon-glow"></div>
            </div>
            <div class="content">
              <div class="title">商品分类</div>
              <div class="value-wrapper">
                <span class="value">{{ categoryCount }}</span>
                <span class="unit">个</span>
              </div>
            </div>
          </div>
          <div class="decoration-line"></div>
          <div class="progress-bar">
            <div class="progress warning" :style="{ width: '86%' }"></div>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="主标题" prop="mainTitle">
        <el-input
          v-model="queryParams.mainTitle"
          placeholder="请输入主标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品状态" prop="goodsStatus">
        <el-select v-model="queryParams.goodsStatus" placeholder="请选择商品状态" clearable>
          <el-option
            v-for="dict in dict.type.goods_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['goods:goods:add']"
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
          v-hasPermi="['goods:goods:edit']"
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
          v-hasPermi="['goods:goods:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['goods:goods:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品ID" align="center" prop="goodsId" />
      <el-table-column label="图片" align="center" prop="photo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="主标题" align="center" prop="mainTitle" />
      <el-table-column label="小标题" align="center" prop="secondaryTitle" />
      <el-table-column label="套餐类别" align="center" prop="categoryName" />
      <el-table-column label="已售件数" align="center" prop="soldNumber" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="积分值" align="center" prop="points" />
      <el-table-column label="商品状态" align="center" prop="goodsStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.goods_status" :value="scope.row.goodsStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['goods:goods:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['goods:goods:remove']"
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

    <!-- 添加或修改商品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图片" prop="photo">
          <image-upload v-model="form.photo"/>
        </el-form-item>
        <el-form-item label="主标题" prop="mainTitle">
          <el-input v-model="form.mainTitle" placeholder="请输入主标题" />
        </el-form-item>
        <el-form-item label="小标题" prop="secondaryTitle">
          <el-input v-model="form.secondaryTitle" placeholder="请输入小标题" />
        </el-form-item>
        <el-form-item label="套餐类别" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入套餐类别" />
        </el-form-item>
        <el-form-item label="商品描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="已售件数" prop="soldNumber">
          <el-input v-model="form.soldNumber" placeholder="请输入已售件数" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="积分值" prop="points">
          <el-input v-model="form.points" placeholder="请输入积分值" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="商品产地" prop="originPlace">
          <el-input v-model="form.originPlace" placeholder="请输入商品产地" />
        </el-form-item>
        <el-form-item label="商品规格" prop="specification">
          <el-input v-model="form.specification" placeholder="请输入商品规格" />
        </el-form-item>
        <el-form-item label="保质期" prop="shelfLife">
          <el-input v-model="form.shelfLife" placeholder="请输入保质期" />
        </el-form-item>
        <el-form-item label="发货地" prop="deliver">
          <el-input v-model="form.deliver" placeholder="请输入发货地" />
        </el-form-item>
        <el-form-item label="广告图片集" prop="advertisement">
          <image-upload v-model="form.advertisement"/>
        </el-form-item>
        <el-form-item label="商品状态" prop="goodsStatus">
          <el-select v-model="form.goodsStatus" placeholder="请选择商品状态">
            <el-option
              v-for="dict in dict.type.goods_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-divider content-position="center">商品分类信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCategory">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCategory">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="categoryList" :row-class-name="rowCategoryIndex" @selection-change="handleCategorySelectionChange" ref="category">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="分类名称" prop="categoryName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.categoryName" placeholder="请输入分类名称" />
            </template>
          </el-table-column>
          <el-table-column label="状态" prop="categoryStatus" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.categoryStatus" placeholder="请选择状态">
                <el-option
                  v-for="dict in dict.type.category_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listGoods, getGoods, delGoods, addGoods, updateGoods } from "@/api/goods/goods";

export default {
  name: "Goods",
  dicts: ['category_status', 'goods_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCategory: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 商品表格数据
      goodsList: [],
      // 商品分类表格数据
      categoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mainTitle: null,
        goodsStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // Add new data properties
      onSaleCount: 0,
      categoryCount: 0,
      isHover1: false,
      isHover2: false,
      isHover3: false,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询商品列表 */
    getList() {
      this.loading = true;
      listGoods(this.queryParams).then(response => {
        this.goodsList = response.rows;
        this.total = response.total;
        // Calculate onSaleCount
        this.onSaleCount = this.goodsList.filter(item => item.goodsStatus === '0').length;
        // Calculate unique categories
        const uniqueCategories = new Set(this.goodsList.map(item => item.categoryId));
        this.categoryCount = uniqueCategories.size;
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
        goodsId: null,
        photo: null,
        mainTitle: null,
        secondaryTitle: null,
        categoryId: null,
        description: null,
        soldNumber: null,
        price: null,
        points: null,
        remark: null,
        originPlace: null,
        specification: null,
        shelfLife: null,
        deliver: null,
        advertisement: null,
        goodsStatus: null
      };
      this.categoryList = [];
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
      this.ids = selection.map(item => item.goodsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const goodsId = row.goodsId || this.ids
      getGoods(goodsId).then(response => {
        this.form = response.data;
        this.categoryList = response.data.categoryList;
        this.open = true;
        this.title = "修改商品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.categoryList = this.categoryList;
          if (this.form.goodsId != null) {
            updateGoods(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoods(this.form).then(response => {
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
      const goodsIds = row.goodsId || this.ids;
      this.$modal.confirm('是否确认删除商品编号为"' + goodsIds + '"的数据项？').then(function() {
        return delGoods(goodsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 商品分类序号 */
    rowCategoryIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 商品分类添加按钮操作 */
    handleAddCategory() {
      let obj = {};
      obj.categoryName = "";
      obj.categoryStatus = "";
      this.categoryList.push(obj);
    },
    /** 商品分类删除按钮操作 */
    handleDeleteCategory() {
      if (this.checkedCategory.length == 0) {
        this.$modal.msgError("请先选择要删除的商品分类据");
      } else {
        const categoryList = this.categoryList;
        const checkedCategory = this.checkedCategory;
        this.categoryList = categoryList.filter(function(item) {
          return checkedCategory.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCategorySelectionChange(selection) {
      this.checkedCategory = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('goods/goods/export', {
        ...this.queryParams
      }, `goods_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style scoped>
.statistics-row {
  margin-bottom: 24px;
}

.statistics-card {
  background: #fff;
  border-radius: 16px;
  padding: 24px;
  height: 180px;
  position: relative;
  transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
  overflow: hidden;
  box-shadow: 0 4px 20px 1px rgba(0, 0, 0, 0.06);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
}

.statistics-card::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 4px;
  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.2), transparent);
  transform: translateX(-100%);
  transition: transform 0.5s;
}

.statistics-card.is-hover {
  transform: translateY(-6px);
  box-shadow: 0 8px 30px rgba(0, 0, 0, 0.12);
}

.statistics-card.is-hover::before {
  transform: translateX(100%);
}

.card-body {
  display: flex;
  align-items: flex-start;
  position: relative;
  z-index: 1;
}

.icon-wrapper {
  width: 64px;
  height: 64px;
  border-radius: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 24px;
  position: relative;
  transition: all 0.3s ease;
  overflow: hidden;
}

.icon-wrapper i {
  font-size: 32px;
  color: #fff;
  z-index: 3;
  filter: drop-shadow(0 2px 4px rgba(0, 0, 0, 0.1));
}

.icon-shadow {
  position: absolute;
  width: 100%;
  height: 100%;
  background: inherit;
  filter: blur(15px);
  opacity: 0.5;
  z-index: 1;
  bottom: -15px;
  border-radius: 50%;
}

.icon-glow {
  position: absolute;
  width: 120%;
  height: 120%;
  background: radial-gradient(circle at center, rgba(255,255,255,0.8), transparent 70%);
  opacity: 0;
  transition: opacity 0.3s;
  z-index: 2;
}

.statistics-card:hover .icon-glow {
  opacity: 0.4;
}

.bg-primary {
  background: linear-gradient(135deg, #1890ff 0%, #36cfc9 100%);
}

.bg-success {
  background: linear-gradient(135deg, #52c41a 0%, #87d068 100%);
}

.bg-warning {
  background: linear-gradient(135deg, #faad14 0%, #ffd666 100%);
}

.content {
  flex: 1;
}

.title {
  font-size: 16px;
  font-weight: 500;
  color: #8c8c8c;
  margin-bottom: 16px;
  letter-spacing: 0.5px;
}

.value-wrapper {
  display: flex;
  align-items: baseline;
  margin-bottom: 16px;
}

.value {
  font-size: 36px;
  font-weight: 600;
  color: #262626;
  line-height: 1;
  letter-spacing: -0.5px;
}

.unit {
  font-size: 14px;
  color: #8c8c8c;
  margin-left: 8px;
}

.decoration-line {
  position: absolute;
  top: 0;
  right: 0;
  width: 100px;
  height: 100px;
  background: linear-gradient(45deg, transparent 48%, rgba(255,255,255,0.1) 50%, transparent 52%);
  transform: translate(50%, -50%) rotate(45deg);
}

.progress-bar {
  height: 6px;
  background: rgba(0, 0, 0, 0.04);
  border-radius: 3px;
  margin-top: 24px;
  overflow: hidden;
  position: absolute;
  bottom: 24px;
  left: 24px;
  right: 24px;
}

.progress {
  height: 100%;
  border-radius: 3px;
  transition: width 1s cubic-bezier(0.4, 0, 0.2, 1);
  position: relative;
  overflow: hidden;
}

.progress::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(90deg, transparent, rgba(255,255,255,0.3), transparent);
  animation: shimmer 2s infinite;
}

@keyframes shimmer {
  0% {
    transform: translateX(-100%);
  }
  100% {
    transform: translateX(100%);
  }
}

@keyframes pulse {
  0% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.08);
  }
  100% {
    transform: scale(1);
  }
}

.statistics-card:hover .icon-wrapper {
  animation: pulse 1.5s infinite;
}
</style>


